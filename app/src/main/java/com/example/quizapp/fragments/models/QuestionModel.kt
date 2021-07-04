package com.example.quizapp.fragments.models

import com.google.gson.annotations.SerializedName

class QuestionModel(
    @SerializedName("id") val id:Long,
    @SerializedName("question") val question:String,
    @SerializedName("answers") val answerModel: AnswerModel,
    @SerializedName("multiple_correct_answers")val isMultiple:Boolean,

    //TODO как заменить со string
    @SerializedName("correct_answers") val correctAnswersModel: CorrectAnswersModel,
    @SerializedName("difficulty")val difficulty:String
)