package com.example.latihan.appscrijodoh.service;

import com.example.latihan.appscrijodoh.entity.User;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AutInterface {

    @POST("/login")
    Call<String> loginuser(@Body User user);

    @Multipart
    @POST("/register")
    Call<String> registrasi(@Part MultipartBody.Part file, @Part("data")RequestBody data);


    @GET("/getalluser/")
    Call<ArrayList<User>> getAllUser(@Header("Authorization") String header);

    @GET("/getlaki/")
    Call<ArrayList<User>> getAllLaki(@Header("Authorization") String header);

    @GET("/getperempuan/")
    Call<ArrayList<User>> getAllPerempuan(@Header("Authorization") String header);
}
