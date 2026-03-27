package com.example.p42_abc.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApi {
    @GET("volumes")
    Call<JsonObject> getBookInfo(
            @Query("q") String titre,
            @Query("maxResults") int maxResultats
    );
}