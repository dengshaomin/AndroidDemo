package com.code.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by dengshaomin on 2018/3/26.
 */

public class GridHeaderView extends LinearLayout {

    public GridHeaderView(Context context) {
        this(context, null);
    }

    public GridHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_gridheader, this);
//        Button button = rootView.findViewById(R.id.button);
//        ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
//        layoutParams.width = 2000;
//        button.setLayoutParams(layoutParams);

    }

}
