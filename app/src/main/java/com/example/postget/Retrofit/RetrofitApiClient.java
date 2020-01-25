package com.example.postget.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://192.168.0.112";

    private RetrofitApiClient() {
    }

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getClient() {

        if (retrofit == null) {
            synchronized (RetrofitApiClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                }
            }

        }
        return retrofit;
    }


}
