package com.example.andyapp.presentation.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentAdminBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.ViewModelFactory
import com.example.andyapp.presentation.setupWithAdapter

class AdminFragment : BaseFragment(R.layout.fragment_admin) {

    private lateinit var binding: FragmentAdminBinding

    private val adminViewModel: AdminViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adminViewModel.getTopics()
        observe()
        binding.btnSave.setOnClickListener {
            adminViewModel.saveQuestion(
                binding.tilQuestion.text.toString(),
                binding.tilCorrectAnswer.text.toString(),
                listOf(
                    binding.tilPossibleAnswer1.text.toString(),
                    binding.tilPossibleAnswer2.text.toString(),
                    binding.tilPossibleAnswer3.text.toString(),
                    binding.tilPossibleAnswer4.text.toString()
                )
            )
        }

    }

    private fun observe() {
        adminViewModel.onGetTopics().observe(viewLifecycleOwner) {
            binding.tvAutoComplete.setupWithAdapter(it) { id ->
                adminViewModel.setId(id)
            }
        }
        adminViewModel.onQuestionSaved().observe(viewLifecycleOwner) {
            if (it) {
                clearFields()
            } else {
                Toast.makeText(
                    requireContext(),
                    "something was wrong please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun clearFields() {
        binding.tilQuestion.setText("")
        binding.tilCorrectAnswer.setText("")
        binding.tilPossibleAnswer1.setText("")
        binding.tilPossibleAnswer2.setText("")
        binding.tilPossibleAnswer3.setText("")
        binding.tilPossibleAnswer4.setText("")
        binding.tvAutoComplete.setText("")
    }

}