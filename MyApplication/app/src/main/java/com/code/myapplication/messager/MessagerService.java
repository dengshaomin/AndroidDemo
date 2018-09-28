package com.code.myapplication.messager;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.code.myapplication.L;

/**
 * Created by dengshaomin on 2018/4/13.
 */

public class MessagerService extends Service {

    private final Messenger mMessenger = new Messenger(new MessagerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();

    }


    private class MessagerHandler extends Handler {

        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case Contants.MSG_FROM_CLIENT:
                    L.e(msg.getData().getString("msg"));
                    Messenger messenger = msg.replyTo;
                    Message replyMsg = Message.obtain(null,Contants.MSG_FROM_SERVER);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg","response form server");
                    replyMsg.setData(bundle);
                    try {
                        messenger.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
