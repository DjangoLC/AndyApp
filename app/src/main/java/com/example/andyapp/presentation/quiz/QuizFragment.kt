package com.example.andyapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentQuizzBinding
import com.example.andyapp.presentation.ViewModelFactory

class QuizFragment : Fragment(R.layout.fragment_quizz) {

    private lateinit var binding: FragmentQuizzBinding

    private val fragmentAdapter: FragmentAdapter by lazy {
        FragmentAdapter(this)
    }

    private val factory = ViewModelFactory()

    private val quizVieModel: QuizViewModel by viewModels() {
        factory
    }

    private val args: QuizFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizzBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questions = args.topic.quiz.map {
            QuestionFragment(it)
        }
        fragmentAdapter.fragments = questions
        binding.container.adapter = fragmentAdapter
    }

}