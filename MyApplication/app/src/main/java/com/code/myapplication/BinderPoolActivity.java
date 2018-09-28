package com.code.myapplication;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BinderPoolActivity extends AppCompatActivity implements OnClickListener {

    Button addBtn, subBtn, areaBtn, perBtn;

    private BinderPool mBinderPool;

    private ICalculate mCalculate;

    private IRect mRect;

    String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        addBtn = findViewById(R.id.add_btn);
        subBtn = findViewById(R.id.sub_btn);
        areaBtn = findViewById(R.id.area_btn);
        perBtn = findViewById(R.id.per_btn);
        addBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        areaBtn.setOnClickListener(this);
        perBtn.setOnClickListener(this);
        getBinderPool();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.add_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG, String.valueOf(mCalculate.add(3, 2)));
                    Toast.makeText(BinderPoolActivity.this, String.valueOf(mCalculate.add(3, 2)), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sub_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG, String.valueOf(mCalculate.sub(3, 2)));
                    Toast.makeText(BinderPoolActivity.this, String.valueOf(mCalculate.sub(3, 2)), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.area_btn:
                    mRect = IrectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG, String.valueOf(mRect.area(3, 2)));
                    Toast.makeText(BinderPoolActivity.this, String.valueOf(mRect.area(3, 2)), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.per_btn:
                    mRect = IrectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG, String.valueOf(mRect.perimeter(3, 2)));
                    Toast.makeText(BinderPoolActivity.this, String.valueOf(mRect.perimeter(3, 2)), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getBinderPool() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
            }
        }).start();
    }
}
