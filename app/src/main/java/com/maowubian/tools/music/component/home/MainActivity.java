package com.maowubian.tools.music.component.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.maowubian.tools.R;
import com.maowubian.tools.databinding.ActivityMainBinding;
import com.zhuruqiao.component.base.BaseActivity;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    public static void launch(Context from, Bundle bundle) {
        Intent intent = new Intent(from, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
    }

    @Override
    protected void init() {
        mDataBinding.mainLayout.setmDragLayout(mDataBinding.dragLayout);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


}
