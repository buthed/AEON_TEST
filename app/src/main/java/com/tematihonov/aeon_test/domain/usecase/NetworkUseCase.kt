package com.tematihonov.aeon_test.domain.usecase

import com.tematihonov.aeon_test.domain.usecase.network.PostLoginUseCase

data class NetworkUseCase(
    val postLoginUseCase: PostLoginUseCase
)
