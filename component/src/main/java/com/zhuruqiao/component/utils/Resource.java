package com.zhuruqiao.component.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;

import com.zhuruqiao.component.base.AppHolder;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public class Resource {

    public static String getString(int resourceId) {
        return AppHolder.getApplication().getString(resourceId);
    }

    public static int getColor(int resourceId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return AppHolder.getApplication().getColor(resourceId);
        } else {
            return AppHolder.getApplication().getResources().getColor(resourceId);
        }

    }

    public static Drawable getDrawable(int resourceId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return AppHolder.getApplication().getDrawable(resourceId);
        } else {
            return AppHolder.getApplication().getResources().getDrawable(resourceId);

        }
    }

    public static float getDimens(int resourceId) {
        return AppHolder.getApplication().getResources().getDimension(resourceId);

    }
}
