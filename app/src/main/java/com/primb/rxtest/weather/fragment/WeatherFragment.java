package com.primb.rxtest.weather.fragment;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.primb.rxtest.R;
import com.primb.rxtest.base.BaseFargment;
import com.primb.rxtest.databinding.FragmentWeatherBinding;
import com.primb.rxtest.weather.activity.AreaActivity;
import com.primb.rxtest.weather.module.WeatherVM;

/**
 * Created by Chen on 2016/12/9.
 * 功能描述：
 */

public class WeatherFragment extends BaseFargment<FragmentWeatherBinding> {
    private WeatherVM weatherObservable;
    public static final int REQUEST_CODE = 0;
    public static final int RESULT_CODE = 9;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        final int index = bundle.getInt("index");

        weatherObservable = new WeatherVM(this);
        binding.setObservable(weatherObservable);
        binding.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AreaActivity.class);
                intent.putExtra("index", index);
                getActivity().startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WeatherFragment.REQUEST_CODE && resultCode == RESULT_CODE) {
            int index = data.getIntExtra("index", -1);
            String cityNum = data.getStringExtra("cityNum");
            weatherObservable.getData(cityNum);
        }
    }

    @BindingAdapter({"weatherImage"})
    public static void weatherImage(ImageView imageView, String weather) {
        if (weather == null)
            return;
        String[] weatherStr = weather.split("转");
        if (weatherStr[0].contains("暴雨")) {
            imageView.setImageResource(R.drawable.weather_baoyu);
        } else if (weatherStr[0].contains("暴雪")) {
            imageView.setImageResource(R.drawable.weather_baoxue);
        } else if (weatherStr[0].contains("大暴雨")) {
            imageView.setImageResource(R.drawable.weather_dabaoyu);
        } else if (weatherStr[0].contains("大雪")) {
            imageView.setImageResource(R.drawable.weather_daxue);
        } else if (weatherStr[0].contains("阵雪")) {
            imageView.setImageResource(R.drawable.weather_zhenxue);
        } else if (weatherStr[0].contains("大雨")) {
            imageView.setImageResource(R.drawable.weather_dayu);
        } else if (weatherStr[0].contains("冻雨")) {
            imageView.setImageResource(R.drawable.weather_dongyu);
        } else if (weatherStr[0].contains("多云")) {
            imageView.setImageResource(R.drawable.weather_duoyun);
        } else if (weatherStr[0].contains("浮尘")) {
            imageView.setImageResource(R.drawable.weather_fuchen);
        } else if (weatherStr[0].contains("降雨伴有冰雹")) {
            imageView.setImageResource(R.drawable.weather_zhenyubingbao);
        } else if (weatherStr[0].contains("雷阵雨")) {
            imageView.setImageResource(R.drawable.weather_leizhenyu);
        } else if (weatherStr[0].contains("强沙尘暴")) {
            imageView.setImageResource(R.drawable.weather_qiangshachenbao);
        } else if (weatherStr[0].contains("晴")) {
            imageView.setImageResource(R.drawable.weather_qing);
        } else if (weatherStr[0].contains("沙尘暴")) {
            imageView.setImageResource(R.drawable.weather_shachenbao);
        } else if (weatherStr[0].contains("小雪")) {
            imageView.setImageResource(R.drawable.weather_xiaoxue);
        } else if (weatherStr[0].contains("小雨")) {
            imageView.setImageResource(R.drawable.weather_xiaoyu);
        } else if (weatherStr[0].contains("扬沙")) {
            imageView.setImageResource(R.drawable.weather_yangsha);
        } else if (weatherStr[0].contains("阴")) {
            imageView.setImageResource(R.drawable.weather_yin);
        } else if (weatherStr[0].contains("雨夹雪")) {
            imageView.setImageResource(R.drawable.weather_yujiaxue);
        } else if (weatherStr[0].contains("阵雨")) {
            imageView.setImageResource(R.drawable.weather_zhenyu);
        } else if (weatherStr[0].contains("中雪")) {
            imageView.setImageResource(R.drawable.weather_zhongxue);
        } else if (weatherStr[0].contains("中雨")) {
            imageView.setImageResource(R.drawable.weather_zhongyu);
        } else if (weatherStr[0].contains("雾")) {
            imageView.setImageResource(R.drawable.weather_wu);
        } else if (weatherStr[0].contains("霾")) {
            imageView.setImageResource(R.drawable.weather_mai);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (weatherObservable.subscribe != null && !weatherObservable.subscribe.isUnsubscribed()) {
            weatherObservable.subscribe.unsubscribe();
        }
    }
}
