package com.example.listviewwithbackend.retorfit;

import com.example.listviewwithbackend.pojo.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BackEndApi {
    @GET("/users/all")
    Call<ArrayList<User>> getAllUsers();
}
