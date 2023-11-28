package com.tematihonov.aeon_test.data.network

import com.tematihonov.aeon_test.data.models.User
import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken
import com.tematihonov.aeon_test.utils.RetrofitConstants.LOGIN
import com.tematihonov.aeon_test.utils.RetrofitConstants.PAYMENTS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @POST(LOGIN)
    suspend fun postLogin(@Body user: User): ResponseToken


    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @GET(PAYMENTS)
    fun getPayments(): ResponsePayments
}