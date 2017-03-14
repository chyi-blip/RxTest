package com.primb.rxtest.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    public View rootView;
    private static Context context;
    public static ArrayList<Activity> activityList = new ArrayList<>();
    public T binding;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        上下文对象
        context = this;
//        把每个继承BesaActivity的加入到activityList中，以方便管理
        activityList.add(this);
//        初始化数据库管理器
        binding = getBinding();
    }

    public T getBinding() {
        return DataBindingUtil.setContentView(this, getLayoutId());
    }

    /*
     * 初始化视图
     * 只做与视图初始化有关的操作，不绑定数据
     */
    public abstract void initView();

    /*
     * 初始化数据
     * 数据处理相关的
     */
    public abstract void initData();

    /*
     * 初始化监听器
     */
    public abstract void initListener();

    /*
     * 程序退出
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
//        杀死进程
        android.os.Process.killProcess(android.os.Process.myPid());
//        通知垃圾回收器回收垃圾
        System.gc();
//        退出
        System.exit(0);
    }

    /*
     *销毁方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
    }
}
