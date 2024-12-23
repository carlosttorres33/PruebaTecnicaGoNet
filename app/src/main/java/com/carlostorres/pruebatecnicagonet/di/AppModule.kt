package com.carlostorres.pruebatecnicagonet.di

import com.carlostorres.pruebatecnicagonet.login.data.remote.LoginService
import com.carlostorres.pruebatecnicagonet.login.data.remote.RemoteLoginDataSource
import com.carlostorres.pruebatecnicagonet.login.data.remote.repository.LoginRepoImpl
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.LoginUseCase
import com.carlostorres.pruebatecnicagonet.utils.Constants.BASE_URL_LOGIN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("RetrofitLogin")
    fun provideRetrofitLogin(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOGIN)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginService(
        @Named("RetrofitLogin") retrofit: Retrofit
    ): LoginService = retrofit.create(LoginService::class.java)

    @Singleton
    @Provides
    fun provideLoginRepo(
        loginService: LoginService
    ): LoginRepo = LoginRepoImpl(loginService)

    @Singleton
    @Provides
    fun provideRemoteLoginDataSource(
        loginRepo: LoginRepo
    ): RemoteLoginDataSource = RemoteLoginDataSource(loginRepo)

    @Singleton
    @Provides
    fun provideLoginUseCase(
        remoteLoginDataSource: RemoteLoginDataSource
    ) : LoginUseCase = LoginUseCase(remoteLoginDataSource)


}