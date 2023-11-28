package com.tematihonov.aeon_test.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tematihonov.aeon_test.R
import com.tematihonov.aeon_test.databinding.FragmentLoginBinding
import com.tematihonov.aeon_test.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignIn.setOnClickListener {
            loginViewModel.login(
                userLogin = binding.edLogin.text.toString().trim(),
                userPassword = binding.edPassword.text.toString().trim())
        }


        loginViewModel.loginResponseToken.observe(viewLifecycleOwner) {
            if (it.data != null) {
                if (it.data.success == "true") {
                    findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
                } else {
                    binding.errorMessage.text = "${it.data.error?.error_msg}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}