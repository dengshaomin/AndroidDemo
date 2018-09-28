package com.code.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.Animator.AnimatorPauseListener;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class ViewAnimationActivity extends AppCompatActivity {

    View translate, scale, roat, alpha, set, translate_share_it, translate_it;

    @RequiresApi(api = VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        translate = findViewById(R.id.translate);
        scale = findViewById(R.id.scale);
        roat = findViewById(R.id.roat);
        alpha = findViewById(R.id.alpha);
        set = findViewById(R.id.set);
        translate_it = findViewById(R.id.translate_it);
        translate_share_it = findViewById(R.id.translate_share_it);
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        translate.startAnimation(translateAnimation);

        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        scale.startAnimation(scaleAnimation);

        Animation roatAnimation = AnimationUtils.loadAnimation(this, R.anim.roat_animation);
        roat.startAnimation(roatAnimation);

        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        alpha.startAnimation(alphaAnimation);

        Animation setAnimation = AnimationUtils.loadAnimation(this, R.anim.set_animation);
        set.startAnimation(setAnimation);

        Animation translate_it_Animation = AnimationUtils.loadAnimation(this, R.anim.translate_it_animation);
        translate_it.startAnimation(translate_it_Animation);

        Animation translate_share_it_Animation = AnimationUtils.loadAnimation(this, R.anim.translate_share_it_animation);
        translate_share_it.startAnimation(translate_share_it_Animation);
    }

    public class CustomInterpolator implements Interpolator {

        public CustomInterpolator(){

        }
        @Override
        public float getInterpolation(float input) {
            return 1-input;
        }
    }
}
