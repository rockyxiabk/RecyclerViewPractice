package com.rocky.recyclerview.type;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocky.recyclerview.ItemDecoration.SpaceItemDecoration;
import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.GridViewAdapter;
import com.rocky.recyclerview.adapter.ListViewAdapter;
import com.rocky.recyclerview.util.DataUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GirdListFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<String> list;
    private GridViewAdapter adapter;

    public GirdListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gird_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_grid));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),5,R.color.colorAccent));
        list = new ArrayList<>();
        adapter = new GridViewAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        initData();
    }

    private void initData() {
        list.addAll(DataUtil.getData(30));
        adapter.notifyDataSetChanged();
    }
}
