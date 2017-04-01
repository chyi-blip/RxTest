package com.primb.rxtest.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.cy.lib.upgrade.Updater;
import com.cy.lib.upgrade.UpdaterConfiguration;
import com.cy.lib.upgrade.callback.UpdateCheckCallback;
import com.cy.lib.upgrade.model.UpdateInfo;
import com.cy.upgrade.interfacedef.UpdateChecker;
import com.primb.rxtest.R;
import com.primb.rxtest.base.BaseActivity;
import com.primb.rxtest.databinding.ActivityMainBinding;
import com.primb.rxtest.weather.adapter.ViewPagerAdapter;
import com.primb.rxtest.weather.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

import static com.primb.rxtest.weather.fragment.WeatherFragment.RESULT_CODE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        WeatherFragment weatherFirst = new WeatherFragment();
        Bundle bundleFirst = new Bundle();
        bundleFirst.putInt("index", 0);
        weatherFirst.setArguments(bundleFirst);

        WeatherFragment weatherSecond = new WeatherFragment();
        Bundle bundleSecond = new Bundle();
        bundleSecond.putInt("index", 1);
        weatherSecond.setArguments(bundleSecond);

        WeatherFragment weatherThirds = new WeatherFragment();
        Bundle bundleThird = new Bundle();
        bundleThird.putInt("index", 2);
        weatherThirds.setArguments(bundleThird);

        fragmentList.add(weatherFirst);
        fragmentList.add(weatherSecond);
        fragmentList.add(weatherThirds);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        binding.viewpager.setAdapter(adapter);
    }

    @Override
    public void initData() {
        //更新配置操作
        final UpdaterConfiguration config = new UpdaterConfiguration();
        config.updateChecker(new UpdateChecker() {
            @Override
            public void check(UpdateCheckCallback callback) {
                //使用全量更新信息
                UpdateInfo updateInfo = new UpdateInfo();
                //设置普通模式的安装
                updateInfo.setInstallType(UpdateInfo.InstallType.NOTIFY_INSTALL);
                updateInfo.setUpdateType(UpdateInfo.UpdateType.TOTAL_UPDATE);
                UpdateInfo.TotalUpdateInfo totalUpdateInfo = new UpdateInfo.TotalUpdateInfo();
                totalUpdateInfo.setApkUrl("http://wap.apk.anzhi.com/data2/apk/201609/05/f06abcb0e2cba4c8ce2301c4b437a492_72932500.apk");
                updateInfo.setTotalUpdateInfo(totalUpdateInfo);
                if (updateInfo != null) {
                    //设置更新信息，这样各模块就可以通过config.getUpdateInfo()共享这个数据了,注意这个方法一定要调用且要在UpdateCheckCallback.onCheckSuccess之前调用
                    config.updateInfo(updateInfo);
                    callback.onCheckSuccess();
                } else {
                    callback.onCheckFail("");
                }
            }
        });
        Updater.getInstance().init(config);
        //此处的Context默认必须为Activity
        Updater.getInstance().check(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WeatherFragment.REQUEST_CODE && resultCode == RESULT_CODE) {
            int index = data.getIntExtra("index", -1);
            fragmentList.get(index).onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        exit();
        return false;
    }
}
