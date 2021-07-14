package com.example.quizapp.fragments.question

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizapp.R
import com.example.quizapp.fragments.models.QuestionModel

class OneAnswerFragment:Fragment() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var listOfRadioBtns:List<RadioButton>
    companion object{
         private const val answers="ANSWERS"
         fun createFragment(questionModel: QuestionModel):OneAnswerFragment{
              val fragment=OneAnswerFragment()
              val bundle=Bundle()
              bundle.putSerializable(answers,questionModel)
              fragment.arguments=bundle
              return fragment
         }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.one_answer_fragment,container,false)
        radioGroup=view.findViewById(R.id.ragioGroup)
        listOfRadioBtns = listOf(
                view.findViewById(R.id.radio1),view.findViewById(R.id.radio2),view.findViewById(R.id.radio3),view.findViewById(R.id.radio4)
                ,view.findViewById(R.id.radio5),view.findViewById(R.id.radio6)
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null){
            val questionModel=arguments?.getSerializable(answers) as QuestionModel
            val answers=questionModel.answerModel.getListOfAnswers()
            for (i in answers.indices){
                listOfRadioBtns[i].text=answers[i]
                listOfRadioBtns[i].visibility=View.VISIBLE
            }
        }
    }

    fun getChosenAnswer():String?{
        listOfRadioBtns.forEach{radioButton ->
            if (radioButton.isChecked){
                return radioButton.text.toString()
            }
        }
        return null
    }

}