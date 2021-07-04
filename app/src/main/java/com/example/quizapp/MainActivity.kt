package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.fragments.question.QuestionFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment=QuestionFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentId,fragment,null)
            .commit()
    }
}