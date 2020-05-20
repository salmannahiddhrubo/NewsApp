package com.example.newsapp;


import com.example.newsapp.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {


    @GET("v2/everything?q=bitcoin&from=2020-03-22&sortBy=publishedAt&apiKey=a19c990534794dfdba6503f692bafbad")
    Call<Headlines>getHeadlines();

    /*@GET ("top-headlines")
    Call<Headlines> getHeadlines(
    @Query("country") String country,
    @Query ("apiKey") String apiKey

    );*/

}
