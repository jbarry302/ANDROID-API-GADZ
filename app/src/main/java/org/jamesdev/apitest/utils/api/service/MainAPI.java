package org.jamesdev.apitest.utils.api.service;

import org.jamesdev.apitest.constants.Constants;
import org.jamesdev.apitest.models.UserInfo;
import org.jamesdev.apitest.retrofits.UserInfoRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAPI {

    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    //getcommentService
    public static UserInfoRequest userService(){
        UserInfoRequest userInfo = getRetrofit().create(UserInfoRequest.class);
        return userInfo;
    }



}
