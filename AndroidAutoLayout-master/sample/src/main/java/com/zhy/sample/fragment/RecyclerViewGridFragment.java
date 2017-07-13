package com.zhy.sample.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.sample.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewGridFragment extends Fragment
{
    private View mView;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_recyclerview_grid, container, false);
        initView();
        return mView;
    }

    private void initView()
    {
        mContext = getActivity();
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);
        mList = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
        {
            mList.add(i + "");
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new MyAdapter());

    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_grid, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {

        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getItemCount()
        {
            return mList.size();
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView)
        {
            super(itemView);
            Random random = new Random();
            itemView.setBackgroundColor(Color.argb(200, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            //recyclerview，注意添加这一行
            AutoUtils.autoSize(itemView, AutoAttr.BASE_HEIGHT);
//            Log.e("", itemView.getLayoutParams().width + "  , " + itemView.getLayoutParams().height);
        }
    }

}
