package com.example.andyapp.presentation.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentRegisterBinding
import com.example.andyapp.databinding.FragmentResultsBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.ViewModelFactory

class ResultsFragment : BaseFragment(R.layout.fragment_results) {

    private lateinit var binding: FragmentResultsBinding

    private val resultViewModel: ResultsViewModel by viewModels {
        factory
    }

    private val resultAdapter: ResultAdapter by lazy {
        ResultAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvResults.apply {
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        resultViewModel.getResults()
        observe()
    }

    private fun observe() {
        resultViewModel.onGetResults().observe(viewLifecycleOwner) {
            resultAdapter.items = it
        }
    }

}