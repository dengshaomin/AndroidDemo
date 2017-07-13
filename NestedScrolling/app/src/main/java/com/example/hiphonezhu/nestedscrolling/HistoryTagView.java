package com.example.hiphonezhu.nestedscrolling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dengshaomin on 2017/7/11.
 */

@SuppressLint("AppCompatCustomView")
public class HistoryTagView extends TextView {
    public HistoryTagView(Context context) {
        this(context, null);
    }

    public HistoryTagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistoryTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackground(getResources().getDrawable(R.drawable.corner_25_f7f7f7));
    }

}
