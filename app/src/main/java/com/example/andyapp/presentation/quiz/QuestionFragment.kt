package com.example.andyapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.andyapp.R
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.databinding.FragmentQuestionBinding
import com.example.andyapp.presentation.ViewModelFactory

class QuestionFragment(private val quiz: Quiz) : Fragment(R.layout.fragment_question) {

    private lateinit var binding: FragmentQuestionBinding

    private val factory = ViewModelFactory()

    private val quizVieModel: QuizViewModel by activityViewModels() {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            quizVieModel.setUserAnswers(
                binding.tvQuestion.text.toString(),
                radioGroup.findViewById<RadioButton>(i).text.toString()
            )
        }
        binding.btnNext.setOnClickListener {
            quizVieModel.nexQuestion()
        }
    }

}