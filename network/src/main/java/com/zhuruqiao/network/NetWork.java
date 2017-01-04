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

    private OkHttpClient getClient(){

        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(OkHttpFactory.getSocketFactory(), OkHttpFactory.getX509TrustManager())
                .hostnameVerifier(OkHttpFactory.getHostNameVerifier())
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new UsualRequestInterceptor())
                .cache(new Cache(httpCacheDirectory, httpCacheSize))
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
