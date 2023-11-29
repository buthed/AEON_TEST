package com.tematihonov.aeon_test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.domain.usecase.NetworkUseCase
import com.tematihonov.aeon_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    val responsePayments = MutableLiveData<Resource<ResponsePayments>>()

    fun getUsersPayments(usersToken: String) {
        viewModelScope.launch {
            networkUseCase.getPaymentsUseCase.invoke(usersToken).onStart {
                responsePayments.postValue(Resource.Loading())
            }.catch {
                responsePayments.postValue(Resource.Error(it.message!!))
            }.collect {
                responsePayments.postValue(Resource.Success(it))
                Log.d("GGG","${it.response}")
            }
        }
    }
}