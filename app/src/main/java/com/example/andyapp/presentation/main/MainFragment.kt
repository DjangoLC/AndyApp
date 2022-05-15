package com.example.andyapp.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andyapp.R
import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.example.andyapp.databinding.FragmentMainBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.ViewModelFactory
import com.google.gson.Gson

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val topicAdapter: TopicsAdapter by lazy {
        TopicsAdapter {
            val action = MainFragmentDirections.actionMainFragmentToDetailTopicFragment(it)
            findNavController().navigate(action)
        }
    }

    private val topicViewModel: TopicsViewModel by viewModels() {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = LinearLayoutManager(requireContext())
        binding.rvTopics.apply {
            adapter = topicAdapter
            layoutManager = lm
        }

        binding.btnAdmin.setOnClickListener {
            findNavController().navigate(R.id.adminFragment)
        }

        binding.btnLog.setOnClickListener {
            findNavController().navigate(R.id.resultsFragment)
        }

        topicViewModel.setup()

        topicViewModel.onTopics().observe(viewLifecycleOwner) {
            topicAdapter.topics = it
        }
    }

}