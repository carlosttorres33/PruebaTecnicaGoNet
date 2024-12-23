package com.carlostorres.pruebatecnicagonet.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(LoginState())

    fun onEvent(event: LoginEvents){
        when(event){
            is LoginEvents.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is LoginEvents.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is LoginEvents.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            LoginEvents.Login -> {}
        }
    }

}