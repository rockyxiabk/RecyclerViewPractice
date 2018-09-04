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
import com.rocky.recyclerview.adapter.callback.Extension;
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
public class SwipeDeleteAdapter extends RecyclerView.Adapter<SwipeDeleteAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private List<String> list;

    public SwipeDeleteAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SwipeDeleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.swipe_delete_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeDeleteAdapter.ViewHolder viewHolder, int i) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements Extension{

        private final ImageView imageView;
        private final TextView tvDes;
        public final TextView tvDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.list_item_iv));
            tvDes = ((TextView) itemView.findViewById(R.id.list_item_tv_des));
            tvDelete = ((TextView) itemView.findViewById(R.id.delete));
        }

        @Override
        public float getDeleteActionWidth() {
            return tvDelete.getWidth();
        }
    }
}
