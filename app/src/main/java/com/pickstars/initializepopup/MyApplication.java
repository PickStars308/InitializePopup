package com.pickstars.initializepopup;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 SharedPreferencesHelper
        SharedPreferencesHelper.init(this,"ProgramData");
    }
}