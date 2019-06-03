package com.samoana.minimalistyoutube.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.samoana.minimalistyoutube.data.MYTResult
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MYTRepository @Inject constructor(private val mytService: MYTService){

    private val KEY = "API KEY HERE"

    fun getSearchResults(searchQuery : String) : LiveData<MYTResult> {
        val data = MutableLiveData<MYTResult>()
        mytService.getSearchResults(searchQuery, KEY).enqueue(object : Callback<MYTResult> {
            override fun onFailure(call: Call<MYTResult>, t: Throwable) {
                Log.e("","",t)
            }
            override fun onResponse(call: Call<MYTResult>, response: Response<MYTResult>) {
                data.value = response.body()
            }
        })
        return data
    }
}