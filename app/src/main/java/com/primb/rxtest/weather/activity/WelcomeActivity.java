package com.primb.rxtest.weather.activity;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.primb.rxtest.R;
import com.primb.rxtest.base.BaseActivity;
import com.primb.rxtest.common.database.GreenDaoManager;
import com.primb.rxtest.common.parse.xml.xml.XmlDataParser;
import com.primb.rxtest.common.utils.PreferencesUtils;
import com.primb.rxtest.common.utils.StreamUtils;
import com.primb.rxtest.databinding.ActivityWelcomeBinding;
import com.primb.rxtest.weather.dao.CityBeanDao;
import com.primb.rxtest.weather.dao.DaoSession;
import com.primb.rxtest.weather.dao.ProvinceBeanDao;
import com.primb.rxtest.weather.module.CityBean;
import com.primb.rxtest.weather.module.ProvinceBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Chen on 2016/12/14.
 * 功能描述：
 */

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding> {

    private DaoSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_welcome_activity_alpha);
        binding.setAnimation(animation);
        session = GreenDaoManager.getInstance().getSession();
        if (session.getProvinceBeanDao().queryBuilder().count() <= 0) {
            InputStream inputStreamProvince = WelcomeActivity.this.getResources().openRawResource(R.raw.weather_provinces);
            insertProvince2SQL(inputStreamProvince);
        }
        if (session.getCityBeanDao().queryBuilder().count() <= 0) {
            InputStream inputStreamCity = WelcomeActivity.this.getResources().openRawResource(R.raw.weather_cities_without_dot);
            insertCity2SQL(inputStreamCity);
        }
    }

    @Override
    public void initListener() {

    }

    private void insertProvince2SQL(final InputStream inputStream) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(StreamUtils.inputStream2String(inputStream));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<String, List<HashMap<String, Object>>>() {
            @Override
            public List<HashMap<String, Object>> call(String s) {
                XmlDataParser xmlDataParser = new XmlDataParser();
                xmlDataParser.putRawData(s);
                List<HashMap<String, Object>> hashMaps = xmlDataParser.getFormatList("RECORDS");
                return hashMaps;
            }
        }).flatMap(new Func1<List<HashMap<String, Object>>, Observable<HashMap<String, Object>>>() {
            @Override
            public Observable<HashMap<String, Object>> call(List<HashMap<String, Object>> mapList) {
                return Observable.from(mapList);
            }
        }).flatMap(new Func1<HashMap<String, Object>, Observable<ProvinceBean>>() {
            @Override
            public Observable<ProvinceBean> call(HashMap<String, Object> map) {
                ProvinceBean bean = new ProvinceBean(null, map.get("name").toString(), map.get("province_id").toString());
                return Observable.just(bean);
            }
        }).map(new Func1<ProvinceBean, Long>() {
            @Override
            public Long call(ProvinceBean provinceBean) {
                ProvinceBeanDao dao = session.getProvinceBeanDao();
                return dao.insert(provinceBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                });
    }

    private void insertCity2SQL(final InputStream inputStream) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(StreamUtils.inputStream2String(inputStream));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<String, List<HashMap<String, Object>>>() {
            @Override
            public List<HashMap<String, Object>> call(String s) {
                XmlDataParser xmlDataParser = new XmlDataParser();
                xmlDataParser.putRawData(s);
                List<HashMap<String, Object>> hashMaps = xmlDataParser.getFormatList("RECORDS");
                return hashMaps;
            }
        }).flatMap(new Func1<List<HashMap<String, Object>>, Observable<HashMap<String, Object>>>() {
            @Override
            public Observable<HashMap<String, Object>> call(List<HashMap<String, Object>> mapList) {
                return Observable.from(mapList);
            }
        }).flatMap(new Func1<HashMap<String, Object>, Observable<CityBean>>() {
            @Override
            public Observable<CityBean> call(HashMap<String, Object> map) {
                CityBean bean = new CityBean(null, map.get("name").toString(), map.get("province_id").toString(), map.get("city_num").toString());
                return Observable.just(bean);
            }
        }).map(new Func1<CityBean, Long>() {
            @Override
            public Long call(CityBean cityBean) {
                CityBeanDao dao = session.getCityBeanDao();
                return dao.insert(cityBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                });
    }

    @BindingAdapter({"setAnimation"})
    public static void setAnimation(final ImageView imageView, Animation animation) {
        imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.getContext().startActivity(new Intent(imageView.getContext(), MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
