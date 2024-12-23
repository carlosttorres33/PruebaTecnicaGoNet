package com.carlostorres.pruebatecnicagonet.home.domain.usecases

import com.carlostorres.pruebatecnicagonet.login.data.local.LocalLoginDataSource

class LogoutUseCase (private val localLoginDataSource: LocalLoginDataSource) {
    operator fun invoke() {
        localLoginDataSource.clearLogin()
    }
}