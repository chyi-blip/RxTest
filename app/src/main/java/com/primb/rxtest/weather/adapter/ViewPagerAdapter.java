package com.primb.rxtest.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.primb.rxtest.common.override.IFragmentPagerAdapter;

import java.util.List;

/**
 * Created by Chen on 2016/12/9.
 * 功能描述：
 */

public class ViewPagerAdapter extends IFragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public ViewPagerAdapter(FragmentManager manager, List<Fragment> fragmentList) {
        super(manager);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
