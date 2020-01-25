package com.example.postget.Interface;

import com.example.postget.Model.ServerResponse;
import com.example.postget.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getUserValidity(@Body User user);

    @GET("/retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getQuote(@Query("user_id") String name);
}
