package com.rocky.recyclerview.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description : com.rocky.recyclerview.base
 *
 * @author : rocky
 * @Create Time : 2018/8/31 上午9:58
 * @Modified Time : 2018/8/31 上午9:58
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    public void onClick(View v) {
        setOnClick(v);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void setOnClick(View v);
}
