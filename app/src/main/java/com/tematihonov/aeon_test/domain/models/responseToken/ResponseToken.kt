package com.tematihonov.aeon_test.domain.models.responseToken

data class ResponseToken(
    val response: Response?,
    val success: String,
    val error: Error?
)