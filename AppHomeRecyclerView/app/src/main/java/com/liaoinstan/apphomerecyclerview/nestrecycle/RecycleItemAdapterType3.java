package com.liaoinstan.apphomerecyclerview.nestrecycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.liaoinstan.apphomerecyclerview.home.Cheese;
import com.liaoinstan.apphomerecyclerview.home.CustomBitmapLoadCallBack;
import com.liaoinstan.apphomerecyclerview.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterType3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Cheese> results;

    //get & set
    public List<Cheese> getResults() {
        return results;
    }

    public RecycleItemAdapterType3(Context context, List<Cheese> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    /////////////////////////////

    private void bind(ItemViewHolder holder, int position){
        x.image().bind(holder.item_recyc_type3_item_img, results.get(position).getImg(),new ImageOptions.Builder().build(),new CustomBitmapLoadCallBack(holder.item_recyc_type3_item_img));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_recyc_type3_item_img;

        public ItemViewHolder(View itemView) {
            super(itemView);
            item_recyc_type3_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type3_item_img);
        }
    }
}
