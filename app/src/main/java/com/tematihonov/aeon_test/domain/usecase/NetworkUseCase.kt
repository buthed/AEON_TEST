package com.tematihonov.aeon_test.domain.usecase

import com.tematihonov.aeon_test.domain.usecase.network.GetPaymentsUseCase
import com.tematihonov.aeon_test.domain.usecase.network.PostLoginUseCase

data class NetworkUseCase(
    val postLoginUseCase: PostLoginUseCase,
    val getPaymentsUseCase: GetPaymentsUseCase
)
