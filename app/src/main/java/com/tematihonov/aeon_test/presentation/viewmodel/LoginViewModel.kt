package com.tematihonov.aeon_test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.aeon_test.data.models.User
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken
import com.tematihonov.aeon_test.domain.usecase.NetworkUseCase
import com.tematihonov.aeon_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    val loginResponseToken = MutableLiveData<Resource<ResponseToken>>()

    fun login(userLogin: String, userPassword: String) {
        viewModelScope.launch {
            networkUseCase.postLoginUseCase.invoke(User(userLogin, userPassword)).onStart {
                loginResponseToken.postValue(Resource.Loading())
            }.catch {
                loginResponseToken.postValue(Resource.Error(it.message!!))
            }.collect {
                loginResponseToken.postValue(Resource.Success(it))
                Log.d("GGG", "$it")
            }
        }
    }
}