package com.example.dengshaomin.repluginhost;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent() != null){
            Activity activity = (Activity) getIntent().getExtras().get("class");
            Log.e("code",activity == null ? "null":activity.getClass().getName());
        }


    }
}
