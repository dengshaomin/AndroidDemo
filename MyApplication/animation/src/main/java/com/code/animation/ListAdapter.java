package com.code.animation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by dengshaomin on 2018/6/7.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;

    Animation mAnimation;

    public ListAdapter(Context context) {
        this.mContext = context;
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.item_animation);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ListItem(mContext);
        }
        ((ListItem) convertView).setText(position + "");
//        convertView.startAnimation(mAnimation);
        return convertView;
    }

}
