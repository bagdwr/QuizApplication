package com.example.quizapp.fragments.network

import com.example.quizapp.fragments.models.QuestionModel
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsApi {
    //https://quizapi.io/api/v1/questions?apiKey=YVdTvM4ru7g3qZ9Pb11KRbEWOtyVAObCdrsOSZyU&category=Linux

    @GET("api/v1/questions")
    fun getQuestions(
        @Query("apiKey") apiKey:String,
        @Query("category")category:String
    ):List<QuestionModel>
}