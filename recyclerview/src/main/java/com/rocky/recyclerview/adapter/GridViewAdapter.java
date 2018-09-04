package com.rocky.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rocky.recyclerview.R;

import java.util.List;

/**
 * Description : com.rocky.recyclerview.adapter
 *
 * @author : rocky
 * @Create Time : 2018/8/30 下午4:58
 * @Modified Time : 2018/8/30 下午4:58
 */
public class GridViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;
    public static final int TYPE_HEADER = 10;
    public static final int TYPE_BOTTOM = 11;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == 0) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new ListViewHolder(view);
        } else if (getItemViewType(i) == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.refresh_header, viewGroup, false);
            return new HeaderViewHolder(view);
        } else if (getItemViewType(i) == TYPE_BOTTOM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_footer, viewGroup, false);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ListViewHolder) {
            ((ListViewHolder) viewHolder).imageView.setBackgroundColor(Color.parseColor(list.get(i)));
        } else if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).imageView.setBackgroundColor(Color.parseColor(list.get(i)));
            ((ItemViewHolder) viewHolder).tvDes.setText(list.get(i));
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
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == list.size() + 1) {
            return TYPE_BOTTOM;
        }
        if (position % 5 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager manager = (GridLayoutManager) layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_BOTTOM || getItemViewType(position) == TYPE_HEADER) {
                        return manager.getSpanCount();
                    }
                    if (getItemViewType(position) != 0) {
                        return 1;
                    } else {
                        return manager.getSpanCount();
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        View itemView = holder.itemView;
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        if (null == layoutParams) {
            return;
        }
        if (holder instanceof HeaderViewHolder || holder instanceof FooterViewHolder) {
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
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

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView tvDes;
        private final TextView tvTime;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.list_item_iv));
            tvDes = ((TextView) itemView.findViewById(R.id.list_item_tv_des));
            tvTime = ((TextView) itemView.findViewById(R.id.list_item_tv_time));
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }
}
