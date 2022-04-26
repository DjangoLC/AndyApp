package com.example.andyapp.presentation.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.andyapp.R
import com.example.andyapp.data.QuestionsRepository
import com.example.andyapp.databinding.FragmentAdminBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminFragment : Fragment(R.layout.fragment_admin) {

    private lateinit var binding: FragmentAdminBinding

    private val repository: QuestionsRepository by lazy {
        QuestionsRepository()
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

        lifecycleScope.launch {
            val data = repository.getTopics()

            withContext(Dispatchers.Main) {

                val items = data.map {
                    it.title
                }
                val adapter = ArrayAdapter(requireContext(),  android.R.layout.simple_spinner_dropdown_item, items)
                binding.tvAutoComplete.setAdapter(adapter)
            }
        }

        binding.btnSave.setOnClickListener {

        }

    }

}