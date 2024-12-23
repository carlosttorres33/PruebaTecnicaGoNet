package com.carlostorres.pruebatecnicagonet.home.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.pruebatecnicagonet.R
import com.carlostorres.pruebatecnicagonet.home.domain.usecases.HomeScreenUseCases
import com.carlostorres.pruebatecnicagonet.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val homeScreenUseCases: HomeScreenUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())

    init {
        getUserData()
    }

    private fun logout() {
        viewModelScope.launch {
            homeScreenUseCases.logoutUseCase()
        }
    }

    private fun getUserData() = viewModelScope.launch {
        try {
            state = state.copy(
                viewState = ViewState.Loading
            )
            val login = homeScreenUseCases.getLoginUseCase()
            val image = homeScreenUseCases.getRandomImageUseCase()
            state = if (login != null) {
                state.copy(
                    viewState = ViewState.Success(login),
                    imageUrl = if (image.isSuccessful) image.body()?.message else null
                )
            } else {
                state.copy(
                    viewState = ViewState.Error(context.getString(R.string.no_data))
                )
            }
        }catch (e: Exception){
            val login = homeScreenUseCases.getLoginUseCase()
            Toast.makeText(context, context.getString(R.string.image_error), Toast.LENGTH_SHORT).show()
            state = if (login != null) {
                state.copy(
                    viewState = ViewState.Success(login),
                    imageUrl = null
                )
            } else {
                state.copy(
                    viewState = ViewState.Error(context.getString(R.string.no_data))
                )
            }
        }
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.OnLogout -> {
                logout()
            }
        }
    }

}