package com.carlostorres.pruebatecnicagonet.home.presentation

import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.utils.ViewState

data class HomeState(
    val viewState: ViewState<LoginResponse> = ViewState.Loading,
    val imageUrl : String? = null,
)
