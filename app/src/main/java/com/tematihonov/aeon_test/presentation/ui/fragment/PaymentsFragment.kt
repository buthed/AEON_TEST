package com.tematihonov.aeon_test.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tematihonov.aeon_test.databinding.FragmentPaymentsBinding
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponseP
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
        _binding = FragmentPaymentsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTitle.text = args.userName
        binding.signOut.setOnClickListener {
            loginViewModel.getUsersPayments(args.userToken)
        } //TODO refactor to auto?

        loginViewModel.responsePayments.observe(viewLifecycleOwner) {
            if (it.data != null) {
                it.data.let { responsePayments ->
                    if (responsePayments.response != null)  paymentsAdapter(responsePayments.response)
                }
            }
        }
    }

    private fun paymentsAdapter(payments: List<ResponseP>) {
        adapter = PaymentsListAdapter()
        adapter.payments = payments
        val layoutManager = LinearLayoutManager(this.context)
        binding.rvPayments.layoutManager = layoutManager
        binding.rvPayments.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}