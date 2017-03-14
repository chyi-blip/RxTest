package com.primb.rxtest.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.primb.rxtest.R;
import com.primb.rxtest.base.BaseActivity;
import com.primb.rxtest.common.database.GreenDaoManager;
import com.primb.rxtest.databinding.ActivityAearBinding;
import com.primb.rxtest.weather.adapter.AreaAdapter;
import com.primb.rxtest.weather.dao.CityBeanDao;
import com.primb.rxtest.weather.dao.ProvinceBeanDao;
import com.primb.rxtest.weather.fragment.WeatherFragment;
import com.primb.rxtest.weather.module.CityBean;
import com.primb.rxtest.weather.module.ProvinceBean;

import java.util.List;

/**
 * Created by Chen on 2016/12/15.
 * 功能描述：
 */

public class AreaActivity extends BaseActivity<ActivityAearBinding> {
    private String province = "";
    private boolean isShow = true;  //true :省份显示  反之城市显示
    private AreaAdapter<ProvinceBean> adapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aear;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        index = getIntent().getIntExtra("index", -1);
        binding.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow) {
                    AreaActivity.this.finish();
                } else {
                    isShow = !isShow;
                    binding.setIsShow(isShow);
                }
            }
        });
        binding.setIsShow(isShow);

        //RecycleView的使用
        binding.provinceRecycler.setHasFixedSize(true);
        binding.provinceRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        binding.cityRecycler.setHasFixedSize(true);
        binding.cityRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        getProvinces(province);
        binding.searchEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getProvinces(editable.toString());
            }
        });
    }

    @Override
    public void initListener() {

    }

    public void getProvinces(String province) {
        List<ProvinceBean> provinceBeanList;
        ProvinceBeanDao beanDao = GreenDaoManager.getInstance().getSession().getProvinceBeanDao();
        if ("".equals(province))
            provinceBeanList = beanDao.queryBuilder().build().list();
        else
            provinceBeanList = beanDao.queryBuilder().where(ProvinceBeanDao.Properties.Name.like(province + "%")).build().list();

        adapter = new AreaAdapter<>(provinceBeanList);
        binding.provinceRecycler.setAdapter(adapter);
        adapter.setListener(new AreaAdapter.OnRecyclerViewClickListener<ProvinceBean>() {
            @Override
            public void OnItemClickListener(View view, ProvinceBean provinceBean) {
                isShow = !isShow;
                binding.setIsShow(isShow);
                getCities(provinceBean.getProvince_id());
            }
        });
    }

    public void getCities(String provinceId) {
        List<CityBean> cityBeanList;
        CityBeanDao beanDao = GreenDaoManager.getInstance().getSession().getCityBeanDao();
        cityBeanList = beanDao.queryBuilder().where(CityBeanDao.Properties.Province_id.eq(provinceId)).build().list();

        AreaAdapter<CityBean> adapter = new AreaAdapter<>(cityBeanList);
        binding.cityRecycler.setAdapter(adapter);
        adapter.setListener(new AreaAdapter.OnRecyclerViewClickListener<CityBean>() {
            @Override
            public void OnItemClickListener(View view, CityBean cityBean) {
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("cityNum", cityBean.getCity_num());
                setResult(WeatherFragment.RESULT_CODE, intent);
                AreaActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isShow) {
            AreaActivity.this.finish();
        } else {
            isShow = !isShow;
            binding.setIsShow(isShow);
        }
        return super.onKeyDown(keyCode, event);
    }
}
