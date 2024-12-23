package com.carlostorres.pruebatecnicagonet.login.data.remote.model

data class LoginRequest(
    val email: String,
    val password: String,
    val username: String
)