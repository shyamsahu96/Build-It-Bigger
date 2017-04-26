package com.oec.shyam.androidlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent=getIntent();
        jokeTv=(TextView)findViewById(R.id.tvjoke);
        try{
            String joke=intent.getStringExtra("joke");
            jokeTv.setText(joke);
        }catch (Exception e){
            Log.e(JokeActivity.class.getSimpleName(),e.getMessage());}
    }
}
