package com.primb.rxtest.weather.module;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Chen on 2016/12/15.
 * 功能描述：
 */
@Entity
public class ProvinceBean {
    @Id
    private Long _id;
    private String name;
    private String province_id;

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

    @Generated(hash = 40612789)
    public ProvinceBean(Long _id, String name, String province_id) {
        this._id = _id;
        this.name = name;
        this.province_id = province_id;
    }

    @Generated(hash = 1410713511)
    public ProvinceBean() {
    }
}
