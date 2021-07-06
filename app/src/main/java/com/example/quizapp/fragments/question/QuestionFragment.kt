package com.example.quizapp.fragments.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizapp.R
import com.example.quizapp.fragments.models.QuestionModel

class QuestionFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModels()
    private lateinit var progressbar: ProgressBar
    private lateinit var tvQuestion: TextView
    private lateinit var nextBtn: Button
    private lateinit var questionPos: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.question_fragment, container, false)
        progressbar = view.findViewById(R.id.progressBar)
        tvQuestion = view.findViewById(R.id.tvQuestion)
        nextBtn = view.findViewById(R.id.nextBtn)
        questionPos = view.findViewById(R.id.questionPos)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeLoadingState()
            .observe(this.viewLifecycleOwner, object : androidx.lifecycle.Observer<Boolean> {
                override fun onChanged(t: Boolean?) {
                    if (t == true) {
                        progressbar.visibility = View.VISIBLE
                    } else {
                        progressbar.visibility = View.GONE
                    }
                }
            })
        viewModel.observeCurrentQuestion().observe(this.viewLifecycleOwner, {
//            if (!it.isMultiple) {
                val answerFragment = OneAnswerFragment.createFragment(it)
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.answers, answerFragment, "${QuestionModel.Type.SINGLE}")
                    .commit()
//            }
            tvQuestion.text = it.question.toString()
        })
        viewModel.observeCurrentQuestionPos().observe(this.viewLifecycleOwner, {
            questionPos.text = it.toString()
        })
        viewModel.observeIsFinished().observe(this.viewLifecycleOwner, {
            if (it == true) {
                nextBtn.text = "Finish"
                nextBtn.setOnClickListener {
                    tvQuestion.text = ""
                    questionPos.text = ""
                }
            } else {
                nextBtn.setOnClickListener {
                    viewModel.buttonClicked()
                }
            }
        })


        viewModel.observeIsNeedToCheckAnswers().observe(this.viewLifecycleOwner, {
            Log.i("myQuestionFragment", "IsNeedToCheckAnswers: $it")
            if(it == QuestionModel.Type.SINGLE) {
                val answer = (childFragmentManager.findFragmentByTag("${QuestionModel.Type.SINGLE}") as OneAnswerFragment)
                        .getChosenAnswer()
                Toast.makeText(context, "answer: $answer", Toast.LENGTH_LONG).show()
            } else if(it == QuestionModel.Type.MULTIPLE) {

            }

        })
    }
}