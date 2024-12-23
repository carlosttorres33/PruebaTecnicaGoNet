package com.carlostorres.pruebatecnicagonet.login.domain.repository

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginRequest
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import retrofit2.Response

interface LoginRepo {

    suspend fun login(loginRequest: LoginRequest) : Response<LoginResponse>

    fun saveLogin(loginResponse: LoginResponse)

    fun getLogin() : LoginResponse?

    fun clearLogin()

}