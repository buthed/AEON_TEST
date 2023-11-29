package com.tematihonov.aeon_test.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tematihonov.aeon_test.R
import com.tematihonov.aeon_test.databinding.FragmentPaymentsBinding
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponseP
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.presentation.ui.rcview.PaymentsListAdapter
import com.tematihonov.aeon_test.presentation.viewmodel.PaymentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel by viewModels<PaymentsViewModel>()

    private lateinit var adapter: PaymentsListAdapter
    private val args: PaymentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation()
        workWithArgs()

        loginViewModel.responsePayments.observe(viewLifecycleOwner) {
            if (it.data != null) {
                it.data.let { responsePayments ->
                    responsePaymentsObserverAction(responsePayments)
                }
            }
        }
    }

    private fun navigation() {
        binding.signOut.setOnClickListener {
            findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment)
        }
    }

    private fun responsePaymentsObserverAction(responsePayments: ResponsePayments) = with(binding) {
        if (responsePayments.response != null) {
            paymentsAdapter(responsePayments.response)
            llMain.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun workWithArgs() {
        binding.toolbarTitle.text = args.userName
        loginViewModel.getUsersPayments(args.userToken)
    }

    private fun paymentsAdapter(payments: List<ResponseP>) {
        adapter = PaymentsListAdapter()
        adapter.payments = payments
        val layoutManager = LinearLayoutManager(this.context)
        binding.apply {
            rvPayments.layoutManager = layoutManager
            rvPayments.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}