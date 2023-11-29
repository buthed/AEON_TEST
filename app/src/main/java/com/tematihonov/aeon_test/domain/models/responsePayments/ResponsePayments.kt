package com.tematihonov.aeon_test.domain.models.responsePayments

import com.tematihonov.aeon_test.domain.models.responseToken.Error

data class ResponsePayments(
    val response: List<ResponseP>?,
    val success: String,
    val error: Error?,
)