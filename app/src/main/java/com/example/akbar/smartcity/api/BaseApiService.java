package com.example.akbar.smartcity.api;

import com.example.akbar.smartcity.model.ResponsModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponsModelUser> register(@Field("nama_lengkap") String nama_lengkap,
                                    @Field("email") String email,
                                    @Field("no_tlpn") String no_tlpn,
                                    @Field("alamat") String alamat,
                                    @Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponsModelUser> login (@Field("username") String username,
                                  @Field("password") String password);
}
