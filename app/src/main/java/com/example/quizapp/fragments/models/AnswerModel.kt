package com.example.quizapp.fragments.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AnswerModel(
    @SerializedName("answer_a") val answer_a:String,
    @SerializedName("answer_b") val answer_b:String,
    @SerializedName("answer_c") val answer_c:String,
    @SerializedName("answer_d") val answer_d:String,
    @SerializedName("answer_e") val answer_e:String,
    @SerializedName("answer_f") val answer_f:String

):Serializable{
    fun getListOfAnswers():List<String>{
        val result:MutableList<String> = mutableListOf()
        if (answer_a!=null){
            result.add(answer_a)
        }
        if (answer_b!=null){
            result.add(answer_b)
        }
        if (answer_c!=null){
            result.add(answer_c)
        }
        if (answer_d!=null){
            result.add(answer_d)
        }
        if (answer_e!=null){
            result.add(answer_e)
        }
        if (answer_f!=null){
            result.add(answer_f)
        }
        return result
    }
}