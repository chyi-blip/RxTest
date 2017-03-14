package com.primb.rxtest.base;

import android.app.Application;
import android.content.Context;

import com.cy.lib.upgrade.LibUpgradeInitializer;
import com.cy.lib.upgrade.Updater;
import com.cy.lib.upgrade.UpdaterConfiguration;
import com.cy.lib.upgrade.callback.UpdateCheckCallback;
import com.cy.lib.upgrade.model.UpdateInfo;
import com.cy.upgrade.interfacedef.UpdateChecker;
import com.primb.rxtest.common.database.GreenDaoManager;

public class MyApplication extends Application {
    public static Context applicationContext;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        LibUpgradeInitializer.init(this);
        applicationContext = this;
        instance = this;
        GreenDaoManager.getInstance();

    }

    public static Context getInstance() {
        return instance;
    }
}
