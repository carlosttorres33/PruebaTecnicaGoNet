package com.carlostorres.pruebatecnicagonet.home.domain.usecases

import com.carlostorres.pruebatecnicagonet.login.domain.usecases.GetLoginUseCase

class HomeScreenUseCases(
    val getLoginUseCase: GetLoginUseCase,
    val logoutUseCase: LogoutUseCase,
    val getRandomImageUseCase: GetRandomImageUseCase
)