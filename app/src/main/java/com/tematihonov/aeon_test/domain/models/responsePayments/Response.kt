package com.tematihonov.aeon_test.domain.models.responsePayments

data class Response(
    val amount: Double,
    val created: Int,
    val id: Int,
    val title: String
)