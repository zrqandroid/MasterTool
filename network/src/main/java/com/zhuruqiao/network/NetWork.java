package com.zhuruqiao.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by zhuruqiao on 2017/1/3.
 * e-mail:563325724@qq.com
 */

public class NetWork {

    private static final int CONNECT_TIME_OUT = 30;

    private static final int READ_TIME_OUT = 60;

    private static final int WRITE_TIME_OUT = 60;

    private OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//设置连接超时
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)//设置读取超时
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)//设置写入超时
                .sslSocketFactory(OkHttpFactory.getSocketFactory())
                .hostnameVerifier(OkHttpFactory.getHostNameVerifier())
                .followRedirects(true)//允许重定向
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new UsualRequestInterceptor())
                .cache(new Cache(httpCacheDirectory, httpCacheSize))//设置缓存
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        AccountManager.get().invalidateToken();
                        try {
                            String token = AccountManager.get().getToken();
                            return response.request().newBuilder().header("token", token).build();
                        } catch (Exception ignored) {

                        }
                        return response.request();
                    }
                })
                .build();

    }
}
