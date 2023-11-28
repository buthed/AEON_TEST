package com.tematihonov.aeon_test.data.repositoryImpl

import com.tematihonov.aeon_test.data.models.User
import com.tematihonov.aeon_test.data.network.ApiService
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken
import com.tematihonov.aeon_test.domain.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): NetworkRepository {

    override suspend fun postLogin(user: User): ResponseToken {
        return apiService.postLogin(user = user)
    }

    override fun getPayments(): ResponsePayments {
        TODO("Not yet implemented")
    }

}