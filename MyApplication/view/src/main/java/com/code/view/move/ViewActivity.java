package com.code.view.move;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.code.view.R;
import com.nineoldandroids.animation.ObjectAnimator;

public class ViewActivity extends AppCompatActivity implements OnClickListener {

    View btn, parent, parent1, btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        btn = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        parent = findViewById(R.id.parent);
        parent1 = findViewById(R.id.parent1);
        btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                parent.scrollBy(-300, -300);
                ((MineScrollView) parent1).smoothScrollTo(-300, -300);
                ObjectAnimator.ofFloat(btn1, "translationX", 0, 200).setDuration(1000).start();
                Animation animation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.translate);
                btn2.startAnimation(animation);
            }
        }, 2000);

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ViewActivity.this, "111", Toast.LENGTH_SHORT).show();
    }
}
