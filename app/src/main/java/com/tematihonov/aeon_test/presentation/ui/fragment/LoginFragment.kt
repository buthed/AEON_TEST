package com.tematihonov.aeon_test.presentation.ui.fragment

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.tematihonov.aeon_test.R
import com.tematihonov.aeon_test.databinding.FragmentLoginBinding
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton()

        loginViewModel.loginResponseToken.observe(viewLifecycleOwner) {
            if (it.data != null) {
                it.data.let { responseToken ->
                    responceTokenObserverAction(responseToken)
                }
            }
        }
    }

    private fun responceTokenObserverAction(responseToken: ResponseToken) = with(binding) {
        if (responseToken.success == "true") {
            val action = LoginFragmentDirections.actionLoginFragmentToPaymentsFragment(
                userName = edLogin.text.toString().trim(),
                userToken = responseToken.response?.token ?: ""
            )
            Navigation.findNavController(buttonSignIn).navigate(action)
        } else {
            errorMessage.text = "Error:\n ${responseToken.error?.error_msg}"
            if (responseToken.error?.error_msg!!.contains("login")) {
                edLogin.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.error))
            } else if (responseToken.error.error_msg.contains("password")) {
                edPassword.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error
                    )
                )
            } else {
                edLogin.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.error))
                edPassword.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error
                    )
                )
            }
            edLogin.text?.clear()
            edPassword.text?.clear()
            llMain.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun signInButton() = with(binding) {
        buttonSignIn.setOnClickListener {
            hideKeyboard()
            if (isOnline(requireContext())) {
                llMain.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                loginViewModel.login(
                    userLogin = edLogin.text.toString().trim(),
                    userPassword = edPassword.text.toString().trim()
                )
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        binding.errorMessage.text = "Error:\n ${resources.getText(R.string.check_internet_con)}"
        return false
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}