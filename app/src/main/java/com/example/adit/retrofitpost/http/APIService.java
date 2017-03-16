package com.example.adit.retrofitpost.http;

import com.example.adit.retrofitpost.http.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by adit on 3/14/17.
 */

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userID") long userId);

}
