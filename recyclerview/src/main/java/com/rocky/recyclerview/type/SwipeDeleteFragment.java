package com.rocky.recyclerview.type;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.callback.SimpleItemTouchHelperCallback;
import com.rocky.recyclerview.adapter.SwipeDeleteAdapter;
import com.rocky.recyclerview.adapter.callback.SwipeItemTouchHelperCallback;
import com.rocky.recyclerview.base.BaseFragment;
import com.rocky.recyclerview.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : com.rocky.recyclerview.type
 *
 * @author : rocky
 * @Create Time : 2018/8/31 上午10:02
 * @Modified Time : 2018/8/31 上午10:02
 */
public class SwipeDeleteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<String> list;
    private SwipeDeleteAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_swipe_delete;
    }

    @Override
    protected void initView(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_list));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//第三个参数，是否反转，意思是加载数据将最新的添加到顶部
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(animator);

        list = new ArrayList<>();
        adapter = new SwipeDeleteAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        //添加ItemTouchHelper
        SwipeItemTouchHelperCallback touchHelperCallback = new SwipeItemTouchHelperCallback(adapter);//实例话Callback
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);//构造ItemTouchHelper
        touchHelper.attachToRecyclerView(recyclerView);//关联recyclerView
    }

    @Override
    protected void initData() {
        list.addAll(DataUtil.getData(15));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setOnClick(View v) {

    }
}
