package com.carlostorres.pruebatecnicagonet.login.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.pruebatecnicagonet.R
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.LoginScreenUseCases
import com.carlostorres.pruebatecnicagonet.login.domain.usecases.LoginUseCase
import com.carlostorres.pruebatecnicagonet.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCases: LoginScreenUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginState())

    private fun login(){
        viewModelScope.launch {

            state = state.copy(
                viewState = ViewState.Loading
            )

            try {
                val response = useCases.loginUseCase(
                    username = state.username,
                    email = state.email,
                    password = state.password
                )

                if (response.isSuccessful){
                    saveLogin(response.body()!!)
                    state = state.copy(
                        viewState = ViewState.Success(response.body())
                    )
                }else{
                    state = state.copy(
                        viewState = ViewState.Error(context.getString(R.string.login_error_message))
                    )
                }

            }catch (e:Exception){
                state = state.copy(
                    viewState = ViewState.Error(context.getString(R.string.login_error_message))
                )
            }

        }
    }

    private fun saveLogin(loginResponse: LoginResponse){
        viewModelScope.launch {
            useCases.saveLoginUseCase(loginResponse)
        }
    }

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
            LoginEvents.Login -> {
                login()
            }
        }
    }

    fun updateViewState(viewState: ViewState<LoginResponse>){
        state = state.copy(viewState = viewState)
    }

}