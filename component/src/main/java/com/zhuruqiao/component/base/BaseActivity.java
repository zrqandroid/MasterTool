package com.zhuruqiao.component.base;

import android.app.ActionBar;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public abstract class BaseActivity<ViewDBinding extends ViewDataBinding> extends AppCompatActivity {

    public Context mContext;

    public ViewDBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        this.mContext = this;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            AppHolder.addActivity(this);
        }
        initView();
        setListener();

    }

    private void initActionBar() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            if (actionBar!=null){
              actionBar.hide();
            }
        }

    }

    protected void setListener() {
        View view = getBackButton();
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.super.onBackPressed();
                }
            });
        }
    }


    private void initView() {
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), getRootLayoutId(), null, false);
        setContentView(mDataBinding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            AppHolder.removeActivity(this);
        }

    }

    public abstract int getRootLayoutId();


    public View getBackButton() {
        return null;
    }
}
