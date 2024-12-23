package com.carlostorres.pruebatecnicagonet.login.domain.usecases

import com.carlostorres.pruebatecnicagonet.login.data.remote.RemoteLoginDataSource
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import retrofit2.Response

class LoginUseCase (
    private val remoteLoginDataSource: RemoteLoginDataSource
) {

    suspend operator fun invoke(
        username: String,
        email: String,
        password: String
    ) : Response<LoginResponse> {
        return remoteLoginDataSource.login(username, email, password)
    }

}