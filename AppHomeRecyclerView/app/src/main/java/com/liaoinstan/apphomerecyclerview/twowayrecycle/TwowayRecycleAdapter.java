package com.liaoinstan.apphomerecyclerview.twowayrecycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liaoinstan.apphomerecyclerview.home.Cheese;
import com.liaoinstan.apphomerecyclerview.home.CustomBitmapLoadCallBack;
import com.liaoinstan.apphomerecyclerview.R;
import com.liaoinstan.apphomerecyclerview.home.SlideShowView;

import org.lucasr.twowayview.widget.SpannableGridLayoutManager;
import org.lucasr.twowayview.widget.StaggeredGridLayoutManager;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class TwowayRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    //type
    public static final int TYPE_SLIDER = 0xff01;
    public static final int TYPE_TYPE2_HEAD = 0xff02;
    public static final int TYPE_TYPE2 = 0xff03;
    public static final int TYPE_TYPE3_HEAD = 0xff04;
    public static final int TYPE_TYPE3 = 0xff05;
    public static final int TYPE_TYPE4 = 0xff06;
    public static final int TYPE_TYPE5 = 0xff07;

    public TwowayRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_SLIDER:
                return new HolderSlider(LayoutInflater.from(parent.getContext()).inflate(R.layout.twowayrecycle_item_slider, parent, false));
            case TYPE_TYPE2_HEAD:
            case TYPE_TYPE3_HEAD:
                return new HolderType2Head(LayoutInflater.from(parent.getContext()).inflate(R.layout.onerecycle_item_type2_head, parent, false));
            case TYPE_TYPE2:
            case TYPE_TYPE3:
            case TYPE_TYPE4:
            case TYPE_TYPE5:
                return new HolderType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.onerecycle_item_type2, parent, false));
            default:
                Log.d("error","viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setItemSpan(holder,position);
        if (holder instanceof HolderSlider){
            bindTypeSlider((HolderSlider) holder, position);
        }else if (holder instanceof HolderType2Head){
            bindType2Head((HolderType2Head) holder, position);
        }else if (holder instanceof HolderType2){
            bindType2((HolderType2) holder, position);
        }
    }

    private void setItemSpan(RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        switch (getItemViewType(position)) {
            case TYPE_SLIDER:
            case TYPE_TYPE2_HEAD:
            case TYPE_TYPE3_HEAD:
            case TYPE_TYPE4:
                lp.span = 6;
                break;
            case TYPE_TYPE2:
                lp.span = 3;
                break;
            case TYPE_TYPE3:
                lp.span = 2;
                break;
            case TYPE_TYPE5:
                lp.span = 3;
                if (position % 3 == 0) {
                    lp.height = 200;
                } else if (position % 5 == 0) {
                    lp.height = 300;
                } else if (position % 7 == 0) {
                    lp.height = 500;
                } else {
                    lp.height = 400;
                }
                break;
        }

        itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_SLIDER;
        }else if (position == 1){
            return TYPE_TYPE2_HEAD;
        }else if (2<=position && position <= 7){
            return TYPE_TYPE2;
        }else if (position == 8){
            return TYPE_TYPE3_HEAD;
        }else if (9<=position && position <= 14){
            return TYPE_TYPE3;
        }else if (15<=position && position <= 18){
            return TYPE_TYPE4;
        }else {
            return TYPE_TYPE5;
        }
    }

    /////////////////////////////

    private void bindTypeSlider(HolderSlider holder, int position){
        String img = "http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg";
        String[] imgs= new String[]{img,img,img,img,img,img,img};
        holder.slideShowView.setImageUrls(imgs);
        holder.slideShowView.startPlay();
    }

    private void bindType2Head(HolderType2Head holder, int position){
    }

    private void bindType2(HolderType2 holder, int position){
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        x.image().bind(holder.item_img_type2, img,new ImageOptions.Builder().build(),new CustomBitmapLoadCallBack(holder.item_img_type2));
    }


    /////////////////////////////

    public class HolderSlider extends RecyclerView.ViewHolder {
        public SlideShowView slideShowView;

        public HolderSlider(View itemView) {
            super(itemView);
            slideShowView = (SlideShowView) itemView.findViewById(R.id.slideShowView);
        }
    }

    public class HolderType2Head extends RecyclerView.ViewHolder {
        public HolderType2Head(View itemView) {
            super(itemView);
        }
    }
    public class HolderType2 extends RecyclerView.ViewHolder {
        public ImageView item_img_type2;

        public HolderType2(View itemView) {
            super(itemView);
            item_img_type2 = (ImageView) itemView.findViewById(R.id.item_img_type2);
        }
    }

}
