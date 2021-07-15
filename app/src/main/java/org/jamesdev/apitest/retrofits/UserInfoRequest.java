package org.jamesdev.apitest.retrofits;

import org.jamesdev.apitest.models.UserInfo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInfoRequest {

    @GET("getinfo.php")
    Call<UserInfo> getUser();

    @GET("allinfo.php")
    Call<List<UserInfo>> getAllUserInfo();

    @POST("addinfo.php")
    Call<UserInfo> addUser(@Body UserInfo user_data);

}
