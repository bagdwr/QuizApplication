package com.example.quizapp.fragments.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.quizapp.R
import com.example.quizapp.fragments.models.QuestionModel
import com.example.quizapp.fragments.network.API_KEY
import com.example.quizapp.fragments.network.NetworkSetuper
import com.example.quizapp.fragments.network.QuestionsApi
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class QuestionFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModels()
    private lateinit var progressbar:ProgressBar
    private lateinit var tvQuestion:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.question_fragment, container, false)
        progressbar=view.findViewById(R.id.progressBar)
        tvQuestion=view.findViewById(R.id.tvQuestion)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeLoadingState().observe(this.viewLifecycleOwner, object:androidx.lifecycle.Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                 if (t==true){
                     progressbar.visibility=View.VISIBLE
                 }else{
                     progressbar.visibility=View.GONE
                 }
            }
        })
        viewModel.observeCurrentQuestion().observe(this.viewLifecycleOwner, {
            tvQuestion.text=it.question.toString()
        })
    }

}