package com.primb.rxtest.weather.module;

import android.databinding.ObservableField;
import android.support.v4.app.Fragment;

import com.primb.rxtest.common.canstant.ApiManger;
import com.primb.rxtest.common.canstant.NetManger;
import com.primb.rxtest.common.utils.PreferencesUtils;
import com.primb.rxtest.weather.fragment.WeatherFragment;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chen on 2016/12/14.
 * 功能描述：天气的ViewModule
 */

public class WeatherVM {
    public final ObservableField<WeatherBean> weather = new ObservableField<>();
    private WeatherFragment fragment;
    private ApiManger appApi;
    public Subscription subscribe;
    public String city_num = "101010100";//默认北京的数据
    private int index;

    public WeatherVM(WeatherFragment fragment) {
        this.fragment = fragment;
        index = fragment.getArguments().getInt("index");
        String cityNum = PreferencesUtils.getString(fragment.getContext(), "cityNum" + index, "false");
        if (!cityNum.equals("false")) {
            getData(cityNum);
        } else {
            getData(city_num);
        }
    }


    public void getData(String city_num) {
        appApi = NetManger.getAppApi();
        subscribe = appApi.getWeatherBean(city_num)
                .subscribeOn(Schedulers.io())//io线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        weather.set(weatherBean);
                    }
                });
        PreferencesUtils.putString(fragment.getContext(), "cityNum" + index, city_num);
    }
}
