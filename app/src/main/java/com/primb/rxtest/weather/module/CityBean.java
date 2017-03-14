package com.primb.rxtest.weather.module;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Chen on 2016/12/15.
 * 功能描述：
 */
@Entity
public class CityBean {
    @Id
    private Long _id;
    private String name;
    private String province_id;
    private String city_num;

    public String getCity_num() {
        return this.city_num;
    }

    public void setCity_num(String city_num) {
        this.city_num = city_num;
    }

    public String getProvince_id() {
        return this.province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Generated(hash = 118315300)
    public CityBean(Long _id, String name, String province_id, String city_num) {
        this._id = _id;
        this.name = name;
        this.province_id = province_id;
        this.city_num = city_num;
    }

    @Generated(hash = 273649691)
    public CityBean() {
    }
}
