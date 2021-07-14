package com.example.quizapp.fragments.question

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
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

    private val isLoading:MutableLiveData<Boolean> = MutableLiveData()
    fun observeLoadingState():LiveData<Boolean> = isLoading

    private val currentPosition:MutableLiveData<String> = MutableLiveData()
    fun observeCurrentQuestionPos():LiveData<String> = currentPosition

    private val isFinished:MutableLiveData<Boolean> =MutableLiveData()
    fun observeIsFinished():LiveData<Boolean> = isFinished

    private val isNeedToCheckAnswers:MutableLiveData<QuestionModel.Type> = MutableLiveData()
    fun observeIsNeedToCheck():LiveData<QuestionModel.Type> = isNeedToCheckAnswers

    //endregion
    init {
       getQuestions()
    }
    fun buttonClicked(){
        if (currentQuestionPosition<listOfQuestions.size){
            isNeedToCheckAnswers.postValue(
                    listOfQuestions[currentQuestionPosition].getQuestionType()
            )
            currentQuestion.postValue(listOfQuestions.get(currentQuestionPosition++))
            currentPosition.postValue("$currentQuestionPosition/20")
            isFinished.postValue(false)
        }else{
            currentPosition.postValue("")
            isFinished.postValue(true)
        }
    }
    fun getQuestions(){
        val disposable= Single.fromCallable{
            isLoading.postValue(true)
            questionsApi.getQuestions(API_KEY,"Linux")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       listOfQuestions.addAll(it)
                if (currentQuestionPosition<listOfQuestions.size){
//                    isNeedToCheckAnswers.postValue(
//                            listOfQuestions[currentQuestionPosition].getQuestionType()
//                    )
                    currentQuestion.postValue(listOfQuestions.get(currentQuestionPosition++))
                }
                currentPosition.postValue("$currentQuestionPosition/20")
                isLoading.postValue(false)
                isFinished.postValue(false)
            },{
                isLoading.postValue(false)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
