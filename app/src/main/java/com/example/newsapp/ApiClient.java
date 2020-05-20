package com.example.newsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://newsapi.org/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;




    public static Retrofit getClient(String baseUrl){

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


  /*  private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }*/
 /*   public static synchronized ApiClient getInstance(){
        if (apiClient==null){
            apiClient =new ApiClient();
        }
        return apiClient;
    }
    public ApiInterface getApi(){
       return retrofit.create(ApiInterface.class);
    }*/
}

