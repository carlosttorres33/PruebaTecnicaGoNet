package com.carlostorres.pruebatecnicagonet.home.presentation

sealed interface HomeEvents {
    data object OnLogout : HomeEvents
}