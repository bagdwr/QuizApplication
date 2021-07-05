package com.example.quizapp.fragments.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CorrectAnswersModel(
    @SerializedName("answer_a_correct")val answer_a_correct:Boolean,
    @SerializedName("answer_b_correct")val answer_b_correct:Boolean,
    @SerializedName("answer_c_correct")val answer_c_correct:Boolean,
    @SerializedName("answer_d_correct")val answer_d_correct:Boolean,
    @SerializedName("answer_e_correct")val answer_e_correct:Boolean,
    @SerializedName("answer_f_correct")val answer_f_correct:Boolean
):Serializable