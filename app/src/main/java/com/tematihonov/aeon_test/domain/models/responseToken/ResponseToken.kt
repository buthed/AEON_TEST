package com.tematihonov.aeon_test.domain.models.responseToken

data class ResponseToken(
    val response: ResponseT?,
    val success: String,
    val error: Error?,
)