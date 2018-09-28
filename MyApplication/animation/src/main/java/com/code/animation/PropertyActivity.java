package com.code.animation;


import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;

public class PropertyActivity extends AppCompatActivity {

    View p1, p2, door, shou;

    EditText et;

    View resume;

    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        door = findViewById(R.id.door);
        shou = findViewById(R.id.shou);
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        p1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shootBall0();
            }
        });
        p2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startX = p2.getX();
                shootBall1();
            }
        });

        et = findViewById(R.id.et);
        resume = findViewById(R.id.resume);
        resume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setSingleLine();
            }
        });

        TestActivity testActivity = new TestActivity(this);
        testActivity.show();
    }


    void shootBall0() {
        ObjectAnimator translateX = ObjectAnimator.ofFloat(p1, "translationX", 0, -100, 0, 100, 0).setDuration(2000);
        translateX.setInterpolator(new BounceInterpolator());
        ObjectAnimator translateY = ObjectAnimator.ofFloat(p1, "translationY", 0, -(screenHeight - door.getHeight())).setDuration(800);
        translateY.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator roate = ObjectAnimator.ofFloat(p1, "rotationY", 0, 360).setDuration(500);
        roate.setRepeatCount(-1);
        AnimatorSet set = new AnimatorSet();
        set.play(translateY).with(roate).before(translateX);
        set.start();
    }

    PathMeasure pathMeasure;

    void shootBall1() {
        Path path = new Path();
        path.moveTo(p2.getX(), p2.getY());
        path.quadTo(0, p2.getY() / 2, p2.getX() * 2, -(p2.getY() - p2.getHeight()));
        pathMeasure = new PathMeasure(path, false);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(p2, "translationX", 0, -100, 0, 100, 0).setDuration(2000);
        translateX.setInterpolator(new BounceInterpolator());
        ValueAnimator translateY = ValueAnimator.ofObject(new MineTypeEvaluator(), new Point(p2.getX(), p2.getY()), new Point(p2.getX(),
                pathMeasure.getLength()))
                .setDuration(800);
        translateY.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                p2.setX(point.x);
                p2.setY(point.y);
            }
        });
        ObjectAnimator roate = ObjectAnimator.ofFloat(p2, "rotationY", 0, 360).setDuration(500);
        roate.setRepeatCount(-1);
        AnimatorSet set = new AnimatorSet();
        set.play(translateY).with(roate).before(translateX);
        set.start();
    }

    public class Point {

        float x;

        float y;

        public Point() {
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    float startX;

    float currentPosition[] = new float[2];

    public class MineTypeEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            Point point = new Point();
            pathMeasure.getPosTan(fraction * (endValue.y - startValue.y), currentPosition, null);
            point.x = currentPosition[0];
            point.y = currentPosition[1];
            return point;
        }
    }
}
