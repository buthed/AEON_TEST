package com.tematihonov.aeon_test.domain.usecase.network

import com.tematihonov.aeon_test.data.models.User
import com.tematihonov.aeon_test.domain.models.responseToken.ResponseToken
import com.tematihonov.aeon_test.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke(user: User): Flow<ResponseToken> = flow {
        emit(networkRepository.postLogin(user = user))
    }.flowOn(Dispatchers.IO)
}