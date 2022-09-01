package com.example.dummy;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface EndPoints {
    @GET("posts")
    Single<Post> getPosts();
}
