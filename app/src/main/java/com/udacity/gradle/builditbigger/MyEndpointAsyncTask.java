package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.shyam.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.oec.shyam.androidlib.JokeActivity;

import java.io.IOException;

/**
 * Created by shyam on 19-Apr-17.
 */

public class MyEndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    String joke;

    @Override
    protected String doInBackground(Context... context) {
        this.context = context[0];
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketellingapp-165113.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }


        try {
            joke = myApiService.tellJoke().execute().getData();
            return joke;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e(MyEndpointAsyncTask.class.getSimpleName(), s);
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("joke", s);
        context.startActivity(intent);

    }
}
