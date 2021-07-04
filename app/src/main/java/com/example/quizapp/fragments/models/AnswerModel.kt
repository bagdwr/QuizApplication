package com.example.quizapp.fragments.models

import com.google.gson.annotations.SerializedName

class AnswerModel(
    @SerializedName("answer_a") val answer_a:String,
    @SerializedName("answer_b") val answer_b:String,
    @SerializedName("answer_c") val answer_c:String,
    @SerializedName("answer_d") val answer_d:String,
    @SerializedName("answer_e") val answer_e:String,
    @SerializedName("answer_f") val answer_f:String

)