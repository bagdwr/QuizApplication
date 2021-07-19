package com.example.quizapp.fragments.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import com.example.quizapp.fragments.models.QuestionModel
import kotlin.math.log

class ManyAnswerFragment: Fragment() {
    private lateinit var listOfCheckBox:List<CheckBox>
    companion object{
        private val questions="QUESTIONS"
         fun createFragment(questionModel: QuestionModel):ManyAnswerFragment{
             val fragment=ManyAnswerFragment()
             val bundle=Bundle()
             bundle.putSerializable(questions,questionModel)
             fragment.arguments=bundle
             return fragment
         }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.multiple_answer_fragment, container, false)
        listOfCheckBox= listOf(
            view.findViewById(R.id.check1),
            view.findViewById(R.id.check2),
            view.findViewById(R.id.check3),
            view.findViewById(R.id.check4),
            view.findViewById(R.id.check5),
            view.findViewById(R.id.check6)
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null){
            val questions=arguments?.getSerializable(questions) as QuestionModel
            val questionsCheck=questions.answerModel.getListOfAnswers()
            for(i in questionsCheck.indices){
                listOfCheckBox[i].text=questionsCheck[i]
                listOfCheckBox[i].visibility=View.VISIBLE
            }
        }
    }

    fun getChosenMultipleAnswers():List<String>?{
        val answers:MutableList<String> = mutableListOf()
        listOfCheckBox.forEach{
            checkBox ->
            if (checkBox.isChecked){
                answers.add(checkBox.text.toString())
            }
        }
        return answers
    }
}