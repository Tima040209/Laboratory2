package com.example.lab2.Model.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"
    private var retrofit: Retrofit? = null

    // Создание Interceptor для добавления заголовка X-Api-Key
    private val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", "H44P7ieWlGsEz9OSElUjpQ==XQePCdxSZgPflulG")
            .build()
        chain.proceed(request)
    }

    fun create(): DogApiService {
        if (retrofit == null) {
            // Добавление Interceptor в OkHttpClient
            val client = OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // Установка OkHttpClient с добавленным Interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(DogApiService::class.java)
    }
}
