package com.maowubian.tools.music.api;

import android.app.Application;

import com.zhuruqiao.component.base.AppHolder;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppHolder.init(this);
    }
}
