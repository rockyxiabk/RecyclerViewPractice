package com.rocky.recyclerview.type;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.callback.SimpleItemTouchHelperCallback;
import com.rocky.recyclerview.adapter.SwipeAdapter;
import com.rocky.recyclerview.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SwipeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<String> list;
    private SwipeAdapter adapter;

    public SwipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });
        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });
        view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });
        view.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(v);
            }
        });
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_list));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//第三个参数，是否反转，意思是加载数据将最新的添加到顶部
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        animator.setChangeDuration(500);
        animator.setRemoveDuration(500);
        recyclerView.setItemAnimator(animator);

        list = new ArrayList<>();
        adapter = new SwipeAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        initData();

        //添加ItemTouchHelper
        SimpleItemTouchHelperCallback touchHelperCallback = new SimpleItemTouchHelperCallback(adapter);//实例话Callback
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);//构造ItemTouchHelper
        touchHelper.attachToRecyclerView(recyclerView);//关联recyclerView
    }

    private void initData() {
        list.addAll(DataUtil.getData(20));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void add(View view) {
        list.add(1, DataUtil.getData());
        adapter.notifyItemRangeChanged(1,list.size());

    }

    public void delete(View view) {
        list.remove(1);
        adapter.notifyItemRangeChanged(1,list.size());

    }

    public void update(View view) {

    }

    public void refresh(View view) {

    }
}
