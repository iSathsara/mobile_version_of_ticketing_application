package com.example.csse_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {

    @GET("/journey")  // last part of url according to page
    Call<List<Posts>> getPosts();

    @GET("/local")
    Call<Response> getBalance();

}
