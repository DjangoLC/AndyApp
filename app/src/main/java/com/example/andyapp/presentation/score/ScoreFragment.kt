package com.example.andyapp.presentation.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentScoreBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.ViewModelFactory
import com.example.andyapp.presentation.quiz.QuizViewModel
import com.example.andyapp.presentation.results.ResultsViewModel

class ScoreFragment : BaseFragment(R.layout.fragment_score) {

    private lateinit var scoreBinding: FragmentScoreBinding

    private val quizVieModel: QuizViewModel by activityViewModels() {
        factory
    }

    private val resultViewModel: ResultsViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scoreBinding = FragmentScoreBinding.inflate(inflater)
        return scoreBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizVieModel.validateAnswers()
        scoreBinding.tvScore.text = "Your score is: ${quizVieModel.getScore()}"
        scoreBinding.btnFinish.setOnClickListener {
            quizVieModel.reset()
            findNavController().navigate(R.id.action_scoreFragment_to_mainFragment)
        }
        resultViewModel.saveResult(quizVieModel.getScore())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                quizVieModel.reset()
                findNavController().navigate(R.id.action_scoreFragment_to_mainFragment)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }

        }

    }

}