package com.zhuruqiao.baseutils.io;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by zhuruqiao on 16/8/9.
 */
public class SpUtils {

    private static SharedPreferences.Editor edit;

    private static SharedPreferences sharedPreferences;

    private static String FILE_NAME = "map_values";


    public static boolean saveData(Context mContext, String tag, Object data) {
        if (edit == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
            edit = sharedPreferences.edit();
        }
        if (data instanceof Boolean) {
            edit.putBoolean(tag, (Boolean) data);

        } else if (data instanceof String) {
            edit.putString(tag, (String) data);

        } else if (data instanceof Integer) {
            edit.putInt(tag, (Integer) data);

        } else if (data instanceof Float) {
            edit.putFloat(tag, (Float) data);

        } else if (data instanceof Long) {
            edit.putLong(tag, (Long) data);

        } else {
            return false;
        }

        return edit.commit();

    }

    public static String getString(Context mContext, String tag, String defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        return sharedPreferences.getString(tag, defaultValue);

    }

    /**
     * getString with default value ""
     *
     * @param mContext
     * @param tag
     * @return
     */
    public static String getString(Context mContext, String tag) {


        return getString(mContext, tag, "");

    }

    public static int getInt(Context mContext, String tag, int defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(tag, defaultValue);

    }

    /**
     * getInt with default value 0
     *
     * @param mContext
     * @param tag
     * @return
     */
    public static int getInt(Context mContext, String tag) {


        return getInt(mContext, tag, 0);

    }

    public static long getLong(Context mContext, String tag, long defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        return sharedPreferences.getLong(tag, defaultValue);

    }

    /**
     * getLong with default value 0
     *
     * @param mContext
     * @param tag
     * @return
     */
    public static long getLong(Context mContext, String tag) {


        return getLong(mContext, tag, 0);

    }

    public static boolean getBoolean(Context mContext, String tag, boolean defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag, defaultValue);

    }

    /**
     * getBoolean with default value false
     *
     * @param mContext
     * @param tag
     * @return
     */
    public static boolean getBoolean(Context mContext, String tag) {


        return getBoolean(mContext, tag, false);

    }

    public static float getFloat(Context mContext, String tag, float defaultValue) {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        return sharedPreferences.getFloat(tag, defaultValue);

    }

    /**
     * getFloat with default value 0
     *
     * @param mContext
     * @param tag
     * @return
     */
    public static float getFloat(Context mContext, String tag) {


        return getFloat(mContext, tag, 0);

    }


}
