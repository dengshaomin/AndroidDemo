package com.code.view.drawable;

import android.graphics.drawable.TransitionDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.code.view.R;

public class DrawableActivity extends AppCompatActivity {

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        final ImageView level = findViewById(R.id.level);
        level.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                level.setImageLevel(1);
            }
        });

        final ImageView transition = findViewById(R.id.transition);
        transition.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable transitionDrawable = (TransitionDrawable) transition.getDrawable();
                if (!flag) {
                    transitionDrawable.startTransition(3000);
                } else {
                    transitionDrawable.reverseTransition(3000);
                }
                flag = !flag;

            }
        });
    }
}
