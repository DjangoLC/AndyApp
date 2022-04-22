package com.example.andyapp.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentMainBinding
import com.example.andyapp.databinding.FragmentRegisterBinding
import com.example.andyapp.presentation.ViewModelFactory
import com.example.andyapp.presentation.login.RegisterViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val topicAdapter: TopicsAdapter by lazy {
        TopicsAdapter {
            val action = MainFragmentDirections.actionMainFragmentToDetailTopicFragment(it)
            findNavController().navigate(action)
        }
    }

    private val factory = ViewModelFactory()

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

        topicViewModel.setup()

        topicViewModel.onTopics().observe(viewLifecycleOwner) {
            topicAdapter.topics = it
        }

    }

}