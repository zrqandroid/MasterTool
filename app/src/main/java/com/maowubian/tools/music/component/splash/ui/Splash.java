package com.maowubian.tools.music.component.splash.ui;

import android.view.View;

import com.maowubian.tools.R;
import com.maowubian.tools.databinding.ActivitySplashBinding;
import com.maowubian.tools.music.component.home.MainActivity;
import com.zhuruqiao.component.base.BaseActivity;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public class Splash extends BaseActivity<ActivitySplashBinding> {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mDataBinding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(Splash.this, null);
            }
        });

    }

}
