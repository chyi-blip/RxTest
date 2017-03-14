package com.primb.rxtest.common.database;


import com.primb.rxtest.base.MyApplication;
import com.primb.rxtest.weather.dao.DaoMaster;
import com.primb.rxtest.weather.dao.DaoSession;

/**
 * Created by Chen on 2016/11/28.
 * 功能描述：数据库管理类
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;


    public GreenDaoManager() {
        //创建一个数据库

        devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), "Test.db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 关闭数据连接
     */
    public void close() {
        if (devOpenHelper != null) {
            devOpenHelper.close();
        }
    }
}
