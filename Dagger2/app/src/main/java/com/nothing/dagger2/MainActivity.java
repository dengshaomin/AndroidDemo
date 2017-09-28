package com.nothing.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    User user;

    @Inject
    Sex sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerActivityComponent.builder().build().inject(this);
        initData();
    }

    private void initData() {
        user.setName("测试");
        user.setSex(sex);
        user.getSex().setMale(true);
        Log.e("code",user.getName());
    }
}
