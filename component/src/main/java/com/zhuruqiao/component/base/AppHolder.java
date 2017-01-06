package com.zhuruqiao.component.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by zhuruqiao on 2017/1/6.
 * e-mail:563325724@qq.com
 */

public class AppHolder {

    private static AppHolder holder;

    private Application.ActivityLifecycleCallbacks callbacks;

    private AppHolder(Application application) {
        this.mApplication = application;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mApplication.registerActivityLifecycleCallbacks(callbacks = new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    addActivity(activity);

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    removeActivity(activity);

                }
            });

        }
    }


    private static Application mApplication;

    private final Stack<Activity> activityStack = new Stack<>();

    public static AppHolder instance() {
        if (holder == null) {
            throw new RuntimeException("You must call method init() in application onCreate()");
        }
        return holder;
    }

    /**
     * 获取全局上下文  用于数据库操作 Toast 获取资源 等
     *
     * @return
     */
    public static Context getApplication() {
        if (mApplication == null) {
            throw new RuntimeException("You must call method init() in application onCreate()");
        }
        return mApplication;
    }

    /**
     * 初始化 务必在application onCreate 中调用
     *
     * @param context
     */
    public static synchronized void init(Application application) {

        if (holder == null) {
            holder = new AppHolder(application);
        }

    }

    /**
     * 将activity 添加到栈中
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        AppHolder.holder.activityStack.add(activity);

    }

    /**
     * 将activity从栈中移除
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        AppHolder.holder.activityStack.remove(activity);

    }

    /**
     * 获取当前activity
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        if (AppHolder.holder.activityStack.isEmpty()) {
            return null;
        } else {
            return AppHolder.holder.activityStack.peek();
        }

    }

    /**
     * 结束当前activity
     */
    public static void finishCurrentActivity() {
        if (getCurrentActivity() != null) {
            getCurrentActivity().finish();
        }

    }


    /**
     * 退出应用程序
     */
    public void appExit() {

        finishAllActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mApplication.unregisterActivityLifecycleCallbacks(callbacks);
        }
        System.exit(0);

    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        while (!AppHolder.holder.activityStack.isEmpty()) {
            Activity activity = AppHolder.holder.activityStack.pop();
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
