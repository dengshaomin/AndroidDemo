package com.example.dengshaomin.cameraandmatrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraTestView cameraTestView = (CameraTestView) findViewById(R.id.cameraView);

    }
}
