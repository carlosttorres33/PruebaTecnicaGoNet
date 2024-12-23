package com.carlostorres.pruebatecnicagonet.login.domain.usecases

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val loginRepo: LoginRepo
) {
    operator fun invoke() : LoginResponse? {
        return loginRepo.getLogin()
    }
}