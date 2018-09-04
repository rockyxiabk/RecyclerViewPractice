package com.rocky.recyclerview.type;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.StageViewAdapter;
import com.rocky.recyclerview.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class StageFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<String> list;
    private StageViewAdapter adapter;

    public StageFragment() {
    }

    @SuppressWarnings("unused")
    public static StageFragment newInstance(int columnCount) {
        StageFragment fragment = new StageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_stage));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        list=new ArrayList<>();
        adapter = new StageViewAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        initData();

    }

    private void initData() {
        list.addAll(DataUtil.getData(30));
        adapter.notifyDataSetChanged();
    }
}
