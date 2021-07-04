package com.example.quizapp.fragments.network

import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkSetuper {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://quizapi.io/")
            .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val questionApi:QuestionsApi= retrofit.create(QuestionsApi::class.java)
}