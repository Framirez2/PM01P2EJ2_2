package com.example.pm01p2ej2_2.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI1 {

    @GET("posts/1")
    Call<Posts1> getPosts1();
}
