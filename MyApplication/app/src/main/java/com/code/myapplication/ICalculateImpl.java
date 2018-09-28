package com.code.myapplication;

import android.os.RemoteException;

/**
 * Created by dengshaomin on 2018/4/17.
 */

public class ICalculateImpl extends ICalculate.Stub{

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int add(int first, int second) throws RemoteException {
        return first + second;
    }

    @Override
    public int sub(int first, int second) throws RemoteException {
        return first - second;
    }
}
