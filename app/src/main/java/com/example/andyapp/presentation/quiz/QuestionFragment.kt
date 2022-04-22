package com.example.andyapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.andyapp.R
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.databinding.FragmentQuestionBinding
import com.example.andyapp.databinding.QuestionRowBinding
import com.google.android.material.chip.Chip

class QuestionFragment(private val quiz: Quiz) : Fragment(R.layout.fragment_question) {

    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvQuestion.text = quiz.question
        quiz.possibleAnswers.forEach { answer ->
            val possibleAnswer = layoutInflater.inflate(R.layout.question_row, null) as RadioButton
            possibleAnswer.id = View.generateViewId()
            possibleAnswer.text = answer
            binding.containerQuestions.addView(possibleAnswer)
        }
        binding.containerQuestions.setOnCheckedChangeListener { radioGroup, i ->
            binding.btnNext.isEnabled = true
        }
    }

}