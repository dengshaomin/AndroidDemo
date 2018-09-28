package com.code.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AnimationActivity extends AppCompatActivity {

    ImageView dolphin, attribute;

    Rotate3dAnimation mRotate3dAnimation;

    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        dolphin = findViewById(R.id.dolphin);
        dolphin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRotate3dAnimation == null) {
                    mRotate3dAnimation = new Rotate3dAnimation(0f, 360, (dolphin.getWidth() / 2), 0, 0, false);
                    mRotate3dAnimation.setFillAfter(true);
                    mRotate3dAnimation.setDuration(3000);
                }
                dolphin.startAnimation(mRotate3dAnimation);
            }
        });
        list = findViewById(R.id.list);
        list.setAdapter(new ListAdapter(this));
        attribute = findViewById(R.id.attribute);

        attribute.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(attribute, "translationX", 0, 100).setDuration(3000).start();
            }
        });


        ValueAnimator alphaAnimator = ValueAnimator.ofFloat(1, 0);
        alphaAnimator.setDuration(1000);
        alphaAnimator.start();
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dolphin.setAlpha((float)animation.getAnimatedValue());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
