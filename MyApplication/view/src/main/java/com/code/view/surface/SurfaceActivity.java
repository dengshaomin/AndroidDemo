package com.code.view.surface;

import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;

import com.code.view.R;

public class SurfaceActivity extends AppCompatActivity {

    MyView mv;

    float m_circle_r = 10;

    Bitmap tmp;

    int screenH, screenW;

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mv = new MyView(this);
//        MySurfaceView mySurfaceView = new MySurfaceView(this);
//        ObjectAnimator.ofFloat(mySurfaceView,"rotationX",0,30).start();
        setContentView(new MySurfaceView(this));
//        FrameLayout frameLayout = findViewById(R.id.container);
//        frameLayout.addView(new MySurfaceView(this));
//        getScreentInfo();
//        FrameLayout container = findViewById(R.id.container);
//        container.addView(new MySurfaceView(this),new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new MyTask(), 1, 16);

//        ImageView iv = findViewById(R.id.iv);
//        Canvas caCanvas = new Canvas();
//        Paint caPaint;
//        caPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        caPaint.setColor(Color.BLUE);
//        caPaint.setStrokeWidth(10);
//        caPaint.setStyle(Style.FILL);
//        Bitmap bitmap = Bitmap.createBitmap(screenW, screenH, Config.ARGB_8888);
//        bitmap.eraseColor(Color.parseColor("#000000"));
//        caCanvas.setBitmap(bitmap);
//        caCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dolphin), 0, 0, caPaint);
//
//        Matrix matrix = new Matrix();
//        Camera camera = new Camera();
//        camera.save();
//        camera.rotate(30,0,0);
//        camera.getMatrix(matrix);
//        camera.restore();
//        matrix.preTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2 );
//        matrix.postTranslate(bitmap.getWidth()/2, bitmap.getHeight()/2);
//        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        iv.setImageBitmap(bitmap);
    }

    void getScreentInfo() {
        Display display = getWindowManager().getDefaultDisplay();
        screenH = display.getHeight();
        screenW = display.getWidth();
    }

    public class MyView extends View {

        MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setStyle(Style.FILL);
            if (m_circle_r >= (getWidth() / 10)) {
                m_circle_r = 0;
            } else {
                m_circle_r++;
            }
                        /* 创建Canvas对象 */
            Canvas mCanvas = new Canvas();
                        /* 创建屏幕大小的缓冲区 tmp*/
            tmp = Bitmap
                    .createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
                        /* 设置将内容绘制在tmp上 */
            mCanvas.setBitmap(tmp);
            //pic 在tmp上
            Bitmap pic = ((BitmapDrawable) getResources().getDrawable(
                    R.drawable.ball)).getBitmap();
            mCanvas.drawBitmap(pic, 0, 0, paint);
            //把5*8个圆绘制在tmp上
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    mCanvas.drawCircle(
                            (getWidth() / 5) * i + (getWidth() / 10),
                            (getHeight() / 8) * j + (getHeight() / 16),
                            m_circle_r, paint);
                }
            }
            //把tmp绘制在物理设备上
            canvas.drawBitmap(tmp, 0, 0, paint);
        }


    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            mv.postInvalidate();
        }
    }
}
