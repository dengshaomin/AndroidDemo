/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.idcardquality;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import com.baidu.idl.authority.AlgorithmOnMainThreadException;
import com.baidu.idl.authority.IDLAuthorityException;
import com.baidu.idl.license.License;
import com.baidu.idl.util.UIThread;

public class IDcardQualityProcess {

    private static IDcardQualityProcess mInstance;
    private static String tokenString;
    private static int mAuthorityStatus;
    private static Throwable loadNativeException = null;

    public IDcardQualityProcess() {
    }

    public static synchronized IDcardQualityProcess getInstance() {
        if (null == mInstance) {
            mInstance = new IDcardQualityProcess();
        }

        return mInstance;
    }

    public native int idcardQualityModelInit(AssetManager var1, String var2);

    public native int idcardQualityCaptchaRelease();

    public native int idcardQualityProcess(byte[] var1, int var2, int var3, boolean var4, int var5);

    public static synchronized int init(String token) throws AlgorithmOnMainThreadException, IDLAuthorityException {
        if (UIThread.isUITread()) {
            throw new AlgorithmOnMainThreadException();
        } else {
            tokenString = token;

            try {
                mAuthorityStatus = License.getInstance().init(tokenString);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            return mAuthorityStatus;
        }
    }

    public int idcardQualityInit(AssetManager assetManager, String modelPath) {
        return mAuthorityStatus == 0 ? this.idcardQualityModelInit(assetManager, modelPath) : mAuthorityStatus;
    }

    public int idcardQualityRelease() {
        return mAuthorityStatus == 0 ? this.idcardQualityCaptchaRelease() : mAuthorityStatus;
    }

    public int idcardQualityDetectionImg(Bitmap img, boolean isfont) {
        if (mAuthorityStatus == 0) {
            int imgHeight = img.getHeight();
            int imgWidth = img.getWidth();
            byte[] imageData = this.getRGBImageData(img);
            return this.idcardQualityProcess(imageData, imgHeight, imgWidth, isfont, 3);
        } else {
            return mAuthorityStatus;
        }
    }

    public static Throwable getLoadSoException() {
        return loadNativeException;
    }

    public byte[] getRGBImageData(Bitmap img) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        int[] pixels = new int[imgWidth * imgHeight];
        img.getPixels(pixels, 0, imgWidth, 0, 0, imgWidth, imgHeight);
        byte[] imageData = new byte[imgWidth * imgHeight * 3];

        for (int getRGBsEnd = 0; getRGBsEnd < imgWidth * imgHeight; ++getRGBsEnd) {
            int color = pixels[getRGBsEnd];
            int getRGBsTime = color >> 16 & 255;
            int green = color >> 8 & 255;
            int blue = color & 255;
            imageData[3 * getRGBsEnd + 0] = (byte) getRGBsTime;
            imageData[3 * getRGBsEnd + 1] = (byte) green;
            imageData[3 * getRGBsEnd + 2] = (byte) blue;
        }
        return imageData;
    }

    static {
        try {
            System.loadLibrary("idl_license");
            System.loadLibrary("idcard_quality.1.0.1");
        } catch (Throwable e) {
            loadNativeException = e;
        }
        mInstance = null;
        mAuthorityStatus = 256;
    }
}
