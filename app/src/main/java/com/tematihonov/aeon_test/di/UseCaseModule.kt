package com.tematihonov.aeon_test.di

import com.tematihonov.aeon_test.data.repositoryImpl.NetworkRepositoryImpl
import com.tematihonov.aeon_test.domain.repository.NetworkRepository
import com.tematihonov.aeon_test.domain.usecase.NetworkUseCase
import com.tematihonov.aeon_test.domain.usecase.network.GetPaymentsUseCase
import com.tematihonov.aeon_test.domain.usecase.network.PostLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideNetworkRepositoryImpl(repository: NetworkRepositoryImpl): NetworkRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideNetworkUseCases(networkRepository: NetworkRepository): NetworkUseCase {
        return NetworkUseCase(
            postLoginUseCase = PostLoginUseCase(networkRepository),
            getPaymentsUseCase = GetPaymentsUseCase(networkRepository)
        )
    }
}