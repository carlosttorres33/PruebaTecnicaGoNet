package com.carlostorres.pruebatecnicagonet.login.data.remote

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginRequest
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


}