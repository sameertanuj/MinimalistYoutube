package com.samoana.minimalistyoutube.repository

import com.samoana.minimalistyoutube.data.MYTResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MYTService {

    @GET("/youtube/v3/search?part=snippet&maxResults=25&type=video")
    fun getSearchResults(@Query("q") searchString: String, @Query("key") key: String): Call<MYTResult>
}