package com.code.myapplication;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;
import com.code.myapplication.messager.Contants;
import com.code.myapplication.messager.MessagerService;
import com.code.view.move.ViewActivity;
import com.code.view.touch.TouchActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    RecyclerView list;

    private Messenger mMessenger;

    private Messenger getMessenger = new Messenger(new MessageHandler());

    private static class MessageHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contants.MSG_FROM_SERVER:
                    L.e(msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        list.addItemDecoration(new GridDividerItemDecoration(30, getResources().getColor(R.color.colorAccent)));
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 3 : 1;
            }
        });
        list.setLayoutManager(gridLayoutManager);
        list.setAdapter(new TAdapter());

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e("code", Looper.getMainLooper() == Looper.myLooper() ? "true" : "false");
                for (int i = 0; i < 5; i++) {
                    e.onNext(i);
//                    Log.e("code", "subscribe:" + i);
                }
                e.onComplete();
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer % 2 == 0;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                if (value == 3) {
//                    this.mDisposable.dispose();
                }
//                Log.e("code", "onNext:" + value);
                Log.e("code", value + " " + (Looper.getMainLooper() == Looper.myLooper() ? "true" : "false"));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                int a = 1;
            }
        });

        UserManager.userId++;
        L.e(this.getClass().getName() + ":" + UserManager.userId);

        User user = new User();
        user.setId("2");
        user.setName("code");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory() + File
                    .separator + "user"
                    + ".txt"));
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initService();

        LottieAnimationView lottie_loading = findViewById(R.id.lottie_loading);
        lottie_loading.playAnimation();

    }

    class student {

        public student(int score) {
            this.score = score;
        }

        public int score;
    }

    private void initService() {
        Intent intent = new Intent(this, MessagerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    class TAdapter extends RecyclerView.Adapter<TViewHolder> {

        @Override
        public TViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TViewHolder(new GridHeaderView(MainActivity.this));
        }

        @Override
        public void onBindViewHolder(final TViewHolder holder, int position) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    Message msg = Message.obtain(null, Contants.MSG_FROM_CLIENT);
                    Bundle data = new Bundle();
                    data.putString("msg", "a message form clien");
                    msg.setData(data);
                    msg.replyTo = getMessenger;
                    try {
                        mMessenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    final SeekBar progressBar = findViewById(R.id.progress);
                    progressBar.setMax(1000);
                    final EditText textView = findViewById(R.id.psTxt);
                    findViewById(R.id.set).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setProgress(Integer.parseInt(textView.getText().toString()));
                        }
                    });

                    startActivity(new Intent(MainActivity.this, TouchActivity.class));

                }
            });
        }


        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class TViewHolder extends RecyclerView.ViewHolder {

        public TViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
