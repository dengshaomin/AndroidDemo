package com.liaoinstan.apphomerecyclerview.nestrecycle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liaoinstan.apphomerecyclerview.home.Cheese;
import com.liaoinstan.apphomerecyclerview.home.CustomBitmapLoadCallBack;
import com.liaoinstan.apphomerecyclerview.home.FullyGridLayoutManager;
import com.liaoinstan.apphomerecyclerview.R;
import com.liaoinstan.apphomerecyclerview.home.SlideShowView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Cheese> results;

    //get & set
    public List<Cheese> getResults() {
        return results;
    }

    //type
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_MAIN = 0xffff;

    public RecycleAdapter(Context context, int srcId, List<Cheese> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_1:
                return new MyViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type1, parent, false));
            case TYPE_2:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type2, parent, false));
            case TYPE_3:
                return new MyViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3, parent, false));
            case TYPE_4:
                return new MyViewHolder4(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type4, parent, false));
            case TYPE_MAIN:
                return new MyViewHolderMain(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_typemain, parent, false));
            default:
                Log.d("error","viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            bindType1((MyViewHolder1) holder, position);
        }else if (holder instanceof MyViewHolder2){
            bindType2((MyViewHolder2) holder, position);
        }else if (holder instanceof MyViewHolder3){
            bindType3((MyViewHolder3) holder, position);
        }else if (holder instanceof MyViewHolder4){
            bindType4((MyViewHolder4) holder, position);
        }else if (holder instanceof MyViewHolderMain){
            bindTypeMain((MyViewHolderMain) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_1;
        }else if (position == 1){
            return TYPE_2;
        }else if (position == 2){
            return TYPE_3;
        }else if (position == 3){
            return TYPE_4;
        }else {
            return TYPE_MAIN;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type){
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                        case TYPE_4:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    /////////////////////////////

    private void bindType1(MyViewHolder1 holder, int position){
        String img = "http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg";
        String[] imgs= new String[]{img,img,img,img,img,img,img};
        holder.slideShowView.setImageUrls(imgs);
        holder.slideShowView.startPlay();
    }

    private void bindType2(MyViewHolder2 holder, int position){
//        holder.item_recyc_type2.setLayoutManager(new LinearLayoutManager(holder.item_recyc_type2.getContext(), LinearLayoutManager.VERTICAL, false));
        holder.item_recyc_type2.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type2.getContext(), 2, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        List<Cheese> results = new ArrayList<Cheese>();
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        results.add(new Cheese(img, "my titles develop by random1"));
        results.add(new Cheese(img, "my titles develop by random2"));
        results.add(new Cheese(img, "my titles develop by random3"));
        results.add(new Cheese(img, "my titles develop by random4"));
        results.add(new Cheese(img, "my titles develop by random5"));
        results.add(new Cheese(img, "my titles develop by moremoremoremoremoremoremoremore random6"));
        holder.item_recyc_type2.setAdapter(new RecycleItemAdapterType2(context, results));
        holder.item_recyc_type2.setNestedScrollingEnabled(false);
    }

    private void bindType3(MyViewHolder3 holder, int position){
//        holder.item_recyc_type3.setLayoutManager(new LinearLayoutManager(holder.item_recyc_type3.getContext(), LinearLayoutManager.VERTICAL, false));
        holder.item_recyc_type3.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type3.getContext(), 3, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        List<Cheese> results = new ArrayList<Cheese>();
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        results.add(new Cheese(img, "my titles develop by random1"));
        results.add(new Cheese(img, "my titles develop by random2"));
        results.add(new Cheese(img, "my titles develop by random3"));
        results.add(new Cheese(img, "my titles develop by random4"));
        results.add(new Cheese(img, "my titles develop by random5"));
        results.add(new Cheese(img, "my titles develop by moremoremoremoremoremoremoremore random6"));
        holder.item_recyc_type3.setAdapter(new RecycleItemAdapterType3(context, results));
        holder.item_recyc_type3.setNestedScrollingEnabled(false);
    }

    private void bindType4(MyViewHolder4 holder, int position){
//        holder.item_recyc_type4.setLayoutManager(new LinearLayoutManager(holder.item_recyc_type4.getContext(), LinearLayoutManager.VERTICAL, false));
        holder.item_recyc_type4.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type4.getContext(), 3, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        List<Cheese> results = new ArrayList<Cheese>();
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        results.add(new Cheese(img, "my titles develop by random1"));
        results.add(new Cheese(img, "my titles develop by random2"));
        results.add(new Cheese(img, "my titles develop by random3"));
        results.add(new Cheese(img, "my titles develop by random4"));
        results.add(new Cheese(img, "my titles develop by random5"));
        results.add(new Cheese(img, "my titles develop by moremoremoremoremoremoremoremore random6"));
        holder.item_recyc_type4.setAdapter(new RecycleItemAdapterType4(context, results));
        holder.item_recyc_type4.setNestedScrollingEnabled(false);
    }

    private void bindTypeMain(MyViewHolderMain holder, int position){
        String img = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
        x.image().bind(holder.item_imgmain, img,new ImageOptions.Builder().build(),new CustomBitmapLoadCallBack(holder.item_imgmain));
    }

    /////////////////////////////

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        public SlideShowView slideShowView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            slideShowView = (SlideShowView) itemView.findViewById(R.id.slideShowView);
        }
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type2;

        public MyViewHolder2(final View itemView) {
            super(itemView);
            item_recyc_type2 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type2);
        }
    }
    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type3;

        public MyViewHolder3(final View itemView) {
            super(itemView);
            item_recyc_type3 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type3);
        }
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type4;

        public MyViewHolder4(final View itemView) {
            super(itemView);
            item_recyc_type4 = (RecyclerView) itemView.findViewById(R.id.item_recyc_type4);
        }
    }

    public class MyViewHolderMain extends RecyclerView.ViewHolder {
        public ImageView item_imgmain;

        public MyViewHolderMain(final View itemView) {
            super(itemView);
            item_imgmain = (ImageView) itemView.findViewById(R.id.item_imgmain);
        }
    }
}
