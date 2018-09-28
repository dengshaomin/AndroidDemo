package com.code.myapplication;

import android.os.RemoteException;

/**
 * Created by dengshaomin on 2018/4/17.
 */

public class IrectImpl extends IRect.Stub {

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int area(int length, int width) throws RemoteException {
        return length * width;
    }

    @Override
    public int perimeter(int length, int width) throws RemoteException {
        return (length + width) * 2;
    }
}
