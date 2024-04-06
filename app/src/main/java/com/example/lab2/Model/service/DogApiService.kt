package com.example.lab2.Model.service

import com.example.lab2.Model.entity.Dog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("dogs")
    fun getDogs(
        @Query("min_weight") minWeight: Int,
        @Query("offset") offset: Int = 0 // По умолчанию смещение равно 0
    ): Call<List<Dog>>
}
