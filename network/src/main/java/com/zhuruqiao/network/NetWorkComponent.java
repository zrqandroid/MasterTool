package com.zhuruqiao.network;

import android.content.Context;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by zhuruqiao on 2017/1/3.
 * e-mail:563325724@qq.com
 */

public class NetWorkComponent {


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
                //设置拦截器 用于获取token或者提交公共参数
                // .addNetworkInterceptor(new UsualRequestInterceptor())
                //设置缓存  .cache()
                //设置认证.authenticator()
                .build();

    }


    private JacksonConverterFactory getJacksonConverterFactory() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);//缩放办理出
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);//解析器忽略未定义定段
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        JacksonConverterFactory factory = JacksonConverterFactory.create(objectMapper);
        return factory;
    }

    /**
     * 获取网络服务的接口
     * @param context
     */
    public void init(Context context) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(getJacksonConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //      MarketService service = retrofit.create(MarketService.class);
    }


}
