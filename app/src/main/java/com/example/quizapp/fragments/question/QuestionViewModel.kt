package com.example.quizapp.fragments.question

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.fragments.models.QuestionModel
import com.example.quizapp.fragments.network.API_KEY
import com.example.quizapp.fragments.network.NetworkSetuper
import com.example.quizapp.fragments.network.QuestionsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class QuestionViewModel : ViewModel() {
    //region Vars
    private val questionsApi: QuestionsApi = NetworkSetuper.questionApi
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val listOfQuestions= mutableListOf<QuestionModel>()
    private var currentQuestionPosition=0
    //endregion

    //region LiveData
    private val currentQuestion:MutableLiveData<QuestionModel> =MutableLiveData()
    fun observeCurrentQuestion():LiveData<QuestionModel> = currentQuestion
    //endregion

    private val isLoading:MutableLiveData<Boolean> = MutableLiveData()
    fun observeLoadingState():LiveData<Boolean> = isLoading
    init {
       getQuestions()
    }

    fun getQuestions(){
        val disposable= Single.fromCallable{
            Thread.sleep(400)
            questionsApi.getQuestions(API_KEY,"Linux")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
//                Toast.makeText(context,"Success", Toast.LENGTH_LONG).show()
                       listOfQuestions.addAll(it)
                if (currentQuestionPosition<listOfQuestions.size){
                    currentQuestion.postValue(listOfQuestions.get(currentQuestionPosition++))
                }
                isLoading.postValue(false)
            },{
                isLoading.postValue(false)
//                Toast.makeText(context,"$it", Toast.LENGTH_LONG).show()
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}