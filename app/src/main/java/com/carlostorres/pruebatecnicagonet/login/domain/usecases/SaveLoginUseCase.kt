package com.carlostorres.pruebatecnicagonet.login.domain.usecases

import com.carlostorres.pruebatecnicagonet.login.data.local.LocalLoginDataSource
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo

class SaveLoginUseCase(
    private val localLoginDataSource: LocalLoginDataSource
) {
    operator fun invoke(loginResponse: LoginResponse) {
        localLoginDataSource.saveLogin(loginResponse)
    }
}