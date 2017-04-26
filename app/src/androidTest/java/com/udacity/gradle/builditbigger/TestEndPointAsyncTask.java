package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;


import com.example.shyam.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by shyam on 19-Apr-17.
 */

@RunWith(AndroidJUnit4.class)
public class TestEndPointAsyncTask {
    Context context;

    @Test
    public void testDoInBackGround() {
        MyApi myApiService = null;
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running against local devappserver
                    .setRootUrl("https://joketellingapp-165113.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override                         public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }




        try {
            String joke = myApiService.tellJoke().execute().getData();
            assertTrue("Null Value of Joke", joke !=null);
        } catch (IOException e) {
            Log.e(MyEndpointAsyncTask.class.getSimpleName(),e.getMessage());
            assert false;
        }

    }
}
