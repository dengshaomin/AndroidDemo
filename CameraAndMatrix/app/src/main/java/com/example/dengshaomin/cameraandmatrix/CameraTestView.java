package com.example.dengshaomin.cameraandmatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dengshaomin on 2017/8/30.
 */
public class CameraTestView extends View {


    private Camera camera;
    private Matrix matrix;
    private Paint paint;
    private int degress = 0;
    private Bitmap bitmap;
    BitmapFactory.Options option;
    Rect rect;
    public CameraTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        camera = new Camera();
        matrix = new Matrix();
        setBackgroundColor(Color.parseColor("#3f51b5"));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ff4081"));
        option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.timg, option);
//        option.inSampleSize = calculateInSampleSize(option, getWidth() / 3, 0);
//        option.inJustDecodeBounds = false;

        rect =new Rect(0,0,Tools.getScreenWidth(getContext()), (int) ((float)option.outHeight / (float)option.outWidth
                * Tools.getScreenWidth(getContext())));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        camera.save();
        camera.rotateX(degress);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        matrix.postTranslate(getWidth() / 2, getHeight() / 2);


//        canvas.drawBitmap(0,0,);
        canvas.concat(matrix);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.timg),rect,rect,paint);

//        this.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                degress += 1;
                invalidate();
//            }
//        },1);

//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.timg, option), matrix, paint);

    }

    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}

