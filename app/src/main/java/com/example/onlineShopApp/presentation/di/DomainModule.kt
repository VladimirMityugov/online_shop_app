package com.example.onlineShopApp.presentation.di


import com.chocky_development.domain.repositories.RepositoryLocal
import com.chocky_development.domain.repositories.RepositoryNetwork
import com.chocky_development.domain.use_cases.UseCaseDataBase
import com.chocky_development.domain.use_cases.UseCaseNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideUseCaseNetwork(repositoryNetwork: RepositoryNetwork): UseCaseNetwork {
        return UseCaseNetwork(repositoryNetwork)
    }

    @Provides
    @ViewModelScoped
    fun provideUseCaseDataBase(repositoryLocal: RepositoryLocal): UseCaseDataBase {
        return UseCaseDataBase(repositoryLocal)
    }

}