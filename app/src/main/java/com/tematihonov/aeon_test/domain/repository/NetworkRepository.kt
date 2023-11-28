package com.tematihonov.aeon_test.domain.repository

import com.tematihonov.aeon_test.data.models.User
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken

interface NetworkRepository {

    suspend fun postLogin(user: User): ResponseToken

    fun getPayments(): ResponsePayments
}