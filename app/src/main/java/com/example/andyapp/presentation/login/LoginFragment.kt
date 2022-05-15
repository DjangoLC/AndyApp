package com.example.andyapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andyapp.R
import com.example.andyapp.databinding.FragmentLoginBinding
import com.example.andyapp.presentation.BaseFragment
import com.example.andyapp.presentation.mainActivity.MainActivity

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val registerViewModel: RegisterViewModel by viewModels() {
        factory
    }

    private lateinit var binding: FragmentLoginBinding

    companion object {
        const val USER = "user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.btnLogin.setOnClickListener {
            registerViewModel.login(
                binding.tieUserName.text.toString(),
                binding.tiePass.text.toString()
            )
        }
        binding.tiePass.addTextChangedListener(
            PasswordWatcher(
                listOf(Validations.CHARACTER, Validations.UPPERCASE),
                object: PasswordWatcher.PasswordListener {
                    override fun onValidate(type: Validations?, isValid: Boolean?) {
                        Log.e("TAG", "$type is valid: $isValid")
                    }

                }
            )
        )
        observe()
    }

    private fun observe() {
        registerViewModel.onLoginSuccess().observe(viewLifecycleOwner) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        registerViewModel.onLoginError().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error login", Toast.LENGTH_SHORT).show()
        }
    }

}