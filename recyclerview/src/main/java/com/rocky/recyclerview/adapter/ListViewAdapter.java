package com.rocky.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.callback.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Description : com.rocky.recyclerview.adapter
 *
 * @author : rocky
 * @Create Time : 2018/8/30 下午4:58
 * @Modified Time : 2018/8/30 下午4:58
 */
public class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private List<String> list;
    private TabClickListener listener;

    public ListViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    public void  setTabClickListener(TabClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return position;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        int itemViewType = getItemViewType(position);
        RecyclerView.ViewHolder holder = null;
        View view = null;
        if (itemViewType == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_banner, viewGroup, false);
            holder = new BannerViewHolder(view);
        } else if (itemViewType == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_tab, viewGroup, false);
            holder = new TabViewHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).imageView.setBackgroundColor(Color.parseColor(list.get(position)));
        } else if (viewHolder instanceof BannerViewHolder) {

        } else if (viewHolder instanceof TabViewHolder) {
            ((TabViewHolder) viewHolder).tab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickTab1();
                }
            });
            ((TabViewHolder) viewHolder).tab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickTab2();
                }
            });
            ((TabViewHolder) viewHolder).tab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickTab3();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (null != list && list.size() > 0) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView tvDes;
        private final TextView tvTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.list_item_iv));
            tvDes = ((TextView) itemView.findViewById(R.id.list_item_tv_des));
            tvTime = ((TextView) itemView.findViewById(R.id.list_item_tv_time));
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.banner));
        }
    }

    public class TabViewHolder extends RecyclerView.ViewHolder {

        private final TextView tab1, tab2, tab3;

        public TabViewHolder(@NonNull View itemView) {
            super(itemView);
            tab1 = ((TextView) itemView.findViewById(R.id.tab1));
            tab2 = ((TextView) itemView.findViewById(R.id.tab2));
            tab3 = ((TextView) itemView.findViewById(R.id.tab3));
        }
    }

    public interface TabClickListener {
        void clickTab1();

        void clickTab2();

        void clickTab3();
    }
}
