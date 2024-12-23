package com.carlostorres.pruebatecnicagonet.login.data.remote.repository

import com.carlostorres.pruebatecnicagonet.login.data.remote.LoginService
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginRequest
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import retrofit2.Response

class LoginRepoImpl (
    private val loginService: LoginService
) : LoginRepo {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return loginService.login(loginRequest)
    }
}