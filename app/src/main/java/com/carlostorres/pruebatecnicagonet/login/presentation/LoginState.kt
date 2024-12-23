package com.carlostorres.pruebatecnicagonet.login.presentation

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.utils.ViewState

data class LoginState(
    val viewState : ViewState<LoginResponse> = ViewState.IDLE,
    val username: String = "Carlos",
    val email: String = "asd@asd.com",
    val password: String = "string",
)
