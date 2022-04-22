package com.example.andyapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.andyapp.R
import com.example.andyapp.data.LoginRepository
import com.example.andyapp.presentation.MainActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val repository: LoginRepository by lazy {
        LoginRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            lifecycleScope.launch {
                val result = repository.login(
                    view.findViewById<TextInputEditText>(R.id.tieUserName).text.toString(),
                    view.findViewById<TextInputEditText>(R.id.tiePass).text.toString()
                )

                if (result) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))

                } else {
                    Toast.makeText(requireContext(), "Error login", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}