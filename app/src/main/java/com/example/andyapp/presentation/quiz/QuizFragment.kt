package com.example.andyapp.presentation.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentQuizzBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.ViewModelFactory

class QuizFragment : BaseFragment(R.layout.fragment_quizz) {

    private lateinit var binding: FragmentQuizzBinding

    private var fragmentAdapter: FragmentAdapter? = null

    private val quizVieModel: QuizViewModel by activityViewModels() {
        factory
    }

    private val viewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            quizVieModel.setCurrentPage(position)
        }
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

        quizVieModel.setTopic(args.topic)
        val questions = args.topic.quiz.map {
            QuestionFragment(it)
        }
        fragmentAdapter = FragmentAdapter(this)
        fragmentAdapter?.fragments = questions
        binding.viewpager.apply {
            adapter = fragmentAdapter
            isUserInputEnabled = false
        }
        binding.viewpager.registerOnPageChangeCallback(viewPagerCallback)
        observe()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAdapter = null
        binding.viewpager.adapter = null
        binding.viewpager.unregisterOnPageChangeCallback(viewPagerCallback)
    }

    private fun observe() {
        quizVieModel.onScoreScreen().observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_quizFragment_to_scoreFragment)
            }
        }
        quizVieModel.onNextQuestion().observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                binding.viewpager.currentItem = binding.viewpager.currentItem + 1
            }
        }
    }

}