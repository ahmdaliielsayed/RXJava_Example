package com.example.rxjavaexample.retrofit.db.dao;

import com.example.rxjavaexample.retrofit.db.entity.Post;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("posts")
//    Observable<ArrayList<Post>> getPosts();
    Single<ArrayList<Post>> getPosts();
}
