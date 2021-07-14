package com.example.quizapp.fragments.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuestionModel(
    @SerializedName("id") val id:Long,
    @SerializedName("question") val question:String,
    @SerializedName("answers") val answerModel: AnswerModel,
    private @SerializedName("multiple_correct_answers")val isMultiple:Boolean,

    //TODO как заменить со string
    @SerializedName("correct_answers") val correctAnswersModel: CorrectAnswersModel,
    @SerializedName("difficulty")val difficulty:String
) : Serializable{

    enum class Type{
        SINGLE, MULTIPLE, UNDEFINED
    }
    fun getQuestionType():Type{
        return if(isMultiple) Type.MULTIPLE else Type.SINGLE
    }
}