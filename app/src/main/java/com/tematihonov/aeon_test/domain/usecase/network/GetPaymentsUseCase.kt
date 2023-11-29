package com.tematihonov.aeon_test.domain.usecase.network

import com.tematihonov.aeon_test.domain.models.responsePayments.ResponsePayments
import com.tematihonov.aeon_test.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetPaymentsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
) {

    suspend operator fun invoke(token: String): Flow<ResponsePayments> = flow {
        emit(networkRepository.getPayments(token))
    }.flowOn(Dispatchers.IO)
}