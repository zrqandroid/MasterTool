package com.zhuruqiao.component.utils;

import android.widget.Toast;

import com.zhuruqiao.component.base.AppHolder;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public class ToastMan {

    public static Toast toast;

    /**
     * 短时间toast
     *
     * @param content
     */
    public static void showShortToast(String content) {
        showToast(content, Toast.LENGTH_LONG);
    }

    /**
     * 长时间toast
     *
     * @param content
     */
    public static void showLongToast(String content) {
        showToast(content, Toast.LENGTH_LONG);
    }

    public static void showToast(String content, int timeType) {
        if (toast == null) {
            toast = Toast.makeText(AppHolder.getApplication(), content, timeType);
        } else {
            toast.setText(content);
            toast.setDuration(timeType);
        }
        toast.show();
    }


}
