package com.jaaveeth.weatherapp.di

import com.jaaveeth.weatherapp.data.network.Network
import com.jaaveeth.weatherapp.data.network.RetrofitAPI
import com.jaaveeth.weatherapp.data.repoimpl.NetworkDataRepositoryImpl
import com.jaaveeth.weatherapp.domain.entities.NetworkDataViewModel
import com.jaaveeth.weatherapp.domain.repo.NetworkDataRepository
import com.jaaveeth.weatherapp.domain.usecase.NetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    @ViewModelScoped
    fun provideRetrofitAPI() : RetrofitAPI {
        // this is just a demo how you can manage your retrofit class
        // you can add custom base url,headers and even create api service from here for every custom modules
       return Network.init().apiService
    }

    @Provides
    @ViewModelScoped
    fun provideNetworkRepository(api: RetrofitAPI): NetworkDataRepository = NetworkDataRepositoryImpl(api)

    @Provides
    @ViewModelScoped
    fun provideNetworkUseCase(repository: NetworkDataRepository) : NetworkUseCase = NetworkUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideNetworkDataViewModel(useCase: NetworkUseCase): NetworkDataViewModel = NetworkDataViewModel(useCase)
}