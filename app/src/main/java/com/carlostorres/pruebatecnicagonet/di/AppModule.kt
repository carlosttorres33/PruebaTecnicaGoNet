package com.carlostorres.pruebatecnicagonet.di

import android.content.Context
import android.content.SharedPreferences
import com.carlostorres.pruebatecnicagonet.login.data.local.LocalLoginDataSource
import com.carlostorres.pruebatecnicagonet.login.data.remote.LoginService
import com.carlostorres.pruebatecnicagonet.login.data.remote.RemoteLoginDataSource
import com.carlostorres.pruebatecnicagonet.login.data.remote.repository.LoginRepoImpl
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.LoginScreenUseCases
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.LoginUseCase
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.SaveLoginUseCase
import com.carlostorres.pruebatecnicagonet.utils.Constants.BASE_URL_LOGIN
import com.carlostorres.pruebatecnicagonet.utils.Constants.PREFERENCES_NAME
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideLoginRepo(
        loginService: LoginService,
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): LoginRepo = LoginRepoImpl(loginService, sharedPreferences, gson)

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

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ) : SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideLocalLoginDataSource(
        loginRepo: LoginRepo
    ): LocalLoginDataSource = LocalLoginDataSource(loginRepo)

    @Singleton
    @Provides
    fun provideLoginScreenUseCases(
        remoteLoginDataSource: RemoteLoginDataSource,
        localLoginDataSource: LocalLoginDataSource,
    ) : LoginScreenUseCases = LoginScreenUseCases(
        loginUseCase = LoginUseCase(remoteLoginDataSource),
        saveLoginUseCase = SaveLoginUseCase(localLoginDataSource)
    )

}