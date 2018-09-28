package com.code.animation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by dengshaomin on 2018/6/7.
 */

public class ListItem extends FrameLayout {

    private View rootView;
    private TextView txt;
    public ListItem(@NonNull Context context) {
        this(context,null);
    }

    public ListItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.item_list,this);
        txt = rootView.findViewById(R.id.txt);
    }

    public void setText(String s){
        txt.setText(s);
    }

}
