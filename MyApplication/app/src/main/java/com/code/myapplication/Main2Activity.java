package com.code.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        L.e(this.getClass().getName() + ":" + UserManager.userId);
        UserManager.userId++;
        Uri uri = getIntent().getData();
        L.e(uri.toString());
//        startActivity(new Intent(this, Main3Activity.class));
//        User user = getIntent().getExtras().getParcelable("code");
//        int a = 1;
    }
}
