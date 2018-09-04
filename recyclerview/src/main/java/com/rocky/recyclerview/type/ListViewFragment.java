package com.rocky.recyclerview.type;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.rocky.recyclerview.ItemDecoration.SpaceItemDecoration;
import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.GridViewAdapter;
import com.rocky.recyclerview.adapter.ListViewAdapter;
import com.rocky.recyclerview.util.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class ListViewFragment extends Fragment implements ListViewAdapter.TabClickListener{

    private RecyclerView recyclerView;
    private List<String> list;
    private ListViewAdapter adapter;
    private ConstraintLayout tab;


    public ListViewFragment() {
    }


    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
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
        tab = ((ConstraintLayout) view.findViewById(R.id.tab));
        view.findViewById(R.id.tab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(10);
            }
        });
        view.findViewById(R.id.tab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(10);
            }
        });
        view.findViewById(R.id.tab3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(10);
            }
        });
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_list));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//第三个参数，是否反转，意思是加载数据将最新的添加到顶部
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (visibleItemPosition + 1 == adapter.getItemCount()) {
                    loadMore(10);
                }
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition > 0) {
                    tab.setVisibility(View.VISIBLE);
                } else {
                    tab.setVisibility(View.GONE);
                }
            }
        });

        //分割线
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),5,R.color.colorAccent));

        //自动定位到中心
        LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);

        list = new ArrayList<>();
        adapter = new ListViewAdapter(getContext(), list);
        adapter.setTabClickListener(this);

        //设置动画
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(500);
//        animator.setChangeDuration(500);
//        animator.setRemoveDuration(500);
//        SlideInLeftAnimator itemAnimator = new SlideInLeftAnimator();
//        recyclerView.setItemAnimator(itemAnimator);

        AnimationAdapter animationAdapter = new SlideInBottomAnimationAdapter(adapter);
        animationAdapter.setFirstOnly(true);
        animationAdapter.setDuration(500);
        animationAdapter.setInterpolator(new OvershootInterpolator(.5f));
        recyclerView.setAdapter(animationAdapter);

        initData();
    }

    private void initData() {
        list.addAll(DataUtil.getData(20));
        adapter.notifyItemRangeInserted(0, list.size());
    }

    private void loadMore(int count) {
        int startP = list.size();
        list.addAll(DataUtil.getData(count));
        adapter.notifyItemRangeInserted(startP, list.size());
    }

    private void refresh(int count) {
        List<String> strings = new ArrayList<>();
        strings.addAll(list.subList(0, 2));//[0,2)
        list.clear();
        list.addAll(strings);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(1);
        list.addAll(DataUtil.getData(count));
        adapter.notifyItemRangeInserted(2,list.size());
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void add(View view) {
        list.add(1, DataUtil.getData());
        adapter.notifyItemRangeChanged(1, list.size());

    }

    public void delete(View view) {
        list.remove(1);
        adapter.notifyItemRangeChanged(1, list.size());

    }

    public void update(View view) {

    }

    public void refresh(View view) {

    }

    @Override
    public void clickTab1() {
        refresh(10);
    }

    @Override
    public void clickTab2() {
        refresh(15);
    }

    @Override
    public void clickTab3() {
        refresh(20);
    }
}
