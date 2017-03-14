package com.primb.rxtest.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFargment<T extends ViewDataBinding> extends android.support.v4.app.Fragment {
    private ViewGroup rootView;
    public T binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = getBinding(inflater, container);
        return binding.getRoot();
    }

    public T getBinding(LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听器
     */
    public void initListener(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
