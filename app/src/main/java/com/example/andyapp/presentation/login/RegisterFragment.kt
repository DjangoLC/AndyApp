package com.example.andyapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentRegisterBinding
import com.example.andyapp.presentation.ViewModelFactory
import com.example.andyapp.presentation.isVisible


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val factory = ViewModelFactory()

    private val registerViewModel: RegisterViewModel by viewModels() {
        factory
    }

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnRegisterMe).setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {
        registerViewModel.doRegister(
            name = binding.tieName.text.toString(),
            username = binding.tieUser.text.toString(),
            address = binding.tieAddress.text.toString(),
            country = binding.tieCity.text.toString(),
            phone = binding.tiePhone.text.toString(),
            email = binding.tieEmail.text.toString(),
            password = binding.tiePass.text.toString(),
            confirmPass = binding.tieComfirmPass.text.toString()
        )
        registerViewModel.state().observe(this.viewLifecycleOwner) { state ->
            binding.progressBar.isVisible(state == RegisterState.Loading)
            when (state) {
                RegisterState.AlreadyExist -> {
                    Toast.makeText(requireContext(), "User already Exist", Toast.LENGTH_SHORT)
                        .show()
                }
                RegisterState.Error -> {
                    Toast.makeText(requireContext(), "Please retry later!", Toast.LENGTH_SHORT)
                        .show()
                }
                RegisterState.Success -> {
                    Toast.makeText(requireContext(), "Success register", Toast.LENGTH_SHORT)
                        .show()
                }

                RegisterState.EmptyFields -> {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()
                }

                RegisterState.PasswordNotMatch -> {
                    Toast.makeText(requireContext(), "Please enter the same password", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    //binding.progressBar.visibility = View.GONE
                }
            }
        }


    }

}