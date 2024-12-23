package com.carlostorres.pruebatecnicagonet.login.presentation

sealed interface LoginEvents {
    data class UsernameChanged(val username: String) : LoginEvents
    data class EmailChanged(val email: String) : LoginEvents
    data class PasswordChanged(val password: String) : LoginEvents
    data object Login : LoginEvents
}