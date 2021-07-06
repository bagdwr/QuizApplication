package com.example.quizapp.fragments.question

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

class OneAnswerFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModels()
    private lateinit var radioGroup: RadioGroup
    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var radio4: RadioButton
    private lateinit var radio5: RadioButton
    private lateinit var radio6: RadioButton

    companion object {
        private const val answers = "ANSWERS"
        fun createFragment(questionModel: QuestionModel): OneAnswerFragment {
            val fragment = OneAnswerFragment()
            val bundle = Bundle()
            bundle.putSerializable(answers, questionModel)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.one_answer_fragment, container, false)
        radioGroup = view.findViewById(R.id.ragioGroup)
        radio1 = view.findViewById(R.id.radio1)
        radio2 = view.findViewById(R.id.radio2)
        radio3 = view.findViewById(R.id.radio3)
        radio4 = view.findViewById(R.id.radio4)
        radio5 = view.findViewById(R.id.radio5)
        radio6 = view.findViewById(R.id.radio6)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOfRadioBtn: List<RadioButton> =
            listOf(
                radio1, radio2, radio3, radio4, radio5, radio6
            )
        if (arguments != null) {
            val questionModel = arguments?.getSerializable(answers) as QuestionModel
            val answers = questionModel.answerModel.getAnswers()
            for (i in answers.indices) {
                listOfRadioBtn[i].text = answers[i]
            }
        }

        listOfRadioBtn.forEach { radioButton ->
            radioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    (parentFragment as QuestionFragment).onAnswerClicked(radioButton.text.toString())
                }
            }
        }
    }
//    fun checkButton(view: View){
//        val radioId=radioGroup.checkedRadioButtonId
//        val radioBtn=view.findViewById<RadioButton>(radioId)
//        Toast.makeText(context,"${radioBtn.text}",Toast.LENGTH_LONG).show()
//    }

}