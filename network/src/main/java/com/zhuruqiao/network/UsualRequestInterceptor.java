package com.zhuruqiao.network;

import android.text.TextUtils;

import com.developer.bsince.log.GOL;
import com.miguan.market.app.SettingManager;
import com.miguan.market.auth.AccountManager;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by oeager on 2015/10/13.
 * email:oeager@foxmail.com
 */
public class UsualRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        GOL.e(chain.request().toString());
        String token =AccountManager.get().getExistToken();
        Request oldRequest = chain.request();
        String oldToken = oldRequest.header("token");
        GOL.e("old token :"+oldToken+",token get = "+token);
        Request newRequest;
        if (TextUtils.isEmpty(oldToken) && !TextUtils.isEmpty(token)) {
            Request.Builder newBuilder = oldRequest.newBuilder()
                    .header("User-Agent", "dl.miguan/http-client-v2.1")
                    .header("token", token);
            if(!SettingManager.getInstance().isNetworkReachable()){
                newRequest = newBuilder.cacheControl(CacheControl.FORCE_CACHE).build();
                GOL.e("force cached get");
            }else {
                newRequest = newBuilder.build();
            }
        }else{
            if(!SettingManager.getInstance().isNetworkReachable()){
                newRequest = oldRequest.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }else{
                newRequest = oldRequest;
            }

        }

        Response response = chain.proceed(newRequest);
        return response;
//        when open server cache will be replaced,here not now;
//        if(SettingManager.getInstance().isNetworkReachable()){
//            int maxAge = 60*60;
//           return response.newBuilder().removeHeader("Pragma")
//                    .header("Cache-Control", "public, max-age=" + maxAge).build();
//        }else {
//            int maxStale = 60*60*24*28;//4 weeks
//            return response.newBuilder().removeHeader("Pragma")
//                    .header("Cache-Control","public, only-if-cached, max-stale="+maxStale).build();
//        }
    }
}
    