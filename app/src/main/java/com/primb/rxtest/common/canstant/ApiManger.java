package com.primb.rxtest.common.canstant;

import com.primb.rxtest.weather.module.WeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chen on 2016/12/13.
 * 功能描述：接口管理类
 */

public interface ApiManger {

    @GET("weather")
    Observable<WeatherBean> getWeatherBean(@Query("cityId") String cityId);
}
