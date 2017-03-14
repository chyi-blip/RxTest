package com.primb.rxtest.common.canstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chen on 2016/12/13.
 * 功能描述：双重锁模式单例
 */

public class NetManger {

    String URL = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=";

    private volatile static ApiManger apiManger;

    public static ApiManger getAppApi() {
        if (apiManger == null) {
            synchronized (NetManger.class) {
                if (apiManger == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://weatherapi.market.xiaomi.com/wtr-v2/")//基础URL 以 / 结尾
                            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                            .build();
                    apiManger = retrofit.create(ApiManger.class);
                }
            }
        }
        return apiManger;
    }

}
