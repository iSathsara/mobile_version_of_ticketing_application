package com.example.csse_app;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// interface for user functionalities
public interface UserService {

    @POST("/recharge")
    Call<UserResponse> saveDetails(@Body UserRequests userRequests);


}
