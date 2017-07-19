package com.example.dengshaomin.directiondialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.left).setOnClickListener(this);
        findViewById(R.id.top).setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
        findViewById(R.id.bottom).setOnClickListener(this);
        findViewById(R.id.center).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.left:
                showMyDialog(Gravity.LEFT);
                break;
            case R.id.top:
                showMyDialog(Gravity.TOP);
                break;
            case R.id.right:
                showMyDialog(Gravity.RIGHT);
                break;
            case R.id.bottom:
                showMyDialog(Gravity.BOTTOM);
                break;
            case R.id.center:
                showMyDialog(Gravity.CENTER);
                break;
        }
    }

    private void showMyDialog(int direction) {


//
        View dialogContentView = null;
        switch (direction) {
            case Gravity.LEFT:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_left, null);
                break;
            case Gravity.TOP:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_top, null);
                break;
            case Gravity.RIGHT:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_right, null);
                break;
            case Gravity.BOTTOM:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_bottom, null);
                break;
            case Gravity.CENTER:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
                break;
            default:
                dialogContentView = LayoutInflater.from(this).inflate(R.layout.dialog_bottom, null);
                break;
        }
        if (direction == Gravity.LEFT || direction == Gravity.RIGHT) {
            FullDialog.newIntance(getSupportFragmentManager()).setGravity(direction).setContentView
                    (dialogContentView, Tools.dip2px(MainActivity.this, 200)).showDidlog();
        } else {
            FullDialog.newIntance(getSupportFragmentManager()).setGravity(direction).setContentView
                    (dialogContentView, Tools.getScreenWidth(MainActivity.this)).setNeedBackGround(true).showDidlog();
        }

    }
}
