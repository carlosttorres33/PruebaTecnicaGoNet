package com.carlostorres.pruebatecnicagonet.login.data.remote

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginRequest
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import retrofit2.Response

class RemoteLoginDataSource (
    private val loginRepo: LoginRepo
) {

    suspend fun login(
        username: String,
        email: String,
        password: String
    ) : Response<LoginResponse> {
        val loginRequest = LoginRequest(
            username = username,
            email = email,
            password = password
        )
        return loginRepo.login(loginRequest)
    }

}