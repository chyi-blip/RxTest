package com.primb.rxtest.common.override;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chen on 2016/12/9.
 * 功能描述：fargment的适配器
 */

public abstract class IFragmentPagerAdapter extends PagerAdapter {
    private FragmentManager manager;

    public IFragmentPagerAdapter(FragmentManager manager) {
        this.manager = manager;
    }

    public abstract Fragment getItem(int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = getItem(position);
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(fragment, "Fragment" + position);
            transaction.commit();
            manager.executePendingTransactions();
        }
        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }
        return fragment.getView();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
