package com.tematihonov.aeon_test.presentation.ui.rcview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tematihonov.aeon_test.databinding.ItemPaymentBinding
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponseP

class PaymentsListAdapter(): RecyclerView.Adapter<PaymentsListAdapter.PaymentsListViewHolder>() {

    var payments: List<ResponseP> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    inner class PaymentsListViewHolder(val binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaymentsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)
        return PaymentsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentsListAdapter.PaymentsListViewHolder, position: Int) {
        val payment = payments[position]
        with(holder.binding) {
            paymentTitle.text = payment.title.toString().ifEmpty { "" }
            paymentCreated.text = payment.created.toString().ifEmpty { "" }
            paymentAmount.text = payment.amount.toString().ifEmpty { "" }
        }
    }

    override fun getItemCount(): Int {
        return payments.size
    }
}