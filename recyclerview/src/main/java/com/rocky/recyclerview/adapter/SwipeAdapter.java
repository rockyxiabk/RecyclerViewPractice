package com.rocky.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private List<String> list;

    public SwipeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SwipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.swipe_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeAdapter.ViewHolder viewHolder, int i) {
        viewHolder.imageView.setBackgroundColor(Color.parseColor(list.get(i)));
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
        Collections.swap(list,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView tvDes;
        private final TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.list_item_iv));
            tvDes = ((TextView) itemView.findViewById(R.id.list_item_tv_des));
            tvTime = ((TextView) itemView.findViewById(R.id.list_item_tv_time));
        }
    }
}
