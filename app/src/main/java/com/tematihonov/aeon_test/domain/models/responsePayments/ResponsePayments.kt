package com.tematihonov.aeon_test.domain.models.responsePayments

data class ResponsePayments(
    val response: List<Response>,
    val success: String
)