package com.carlostorres.pruebatecnicagonet.login.data.local

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo

class LocalLoginDataSource(
    private val loginRepo: LoginRepo
) {

    fun saveLogin(loginResponse: LoginResponse) {
        loginRepo.saveLogin(loginResponse)
    }

    fun getLogin(): LoginResponse? {
        return loginRepo.getLogin()
    }

    fun clearLogin() {
        loginRepo.clearLogin()
    }

}