package com.carlostorres.pruebatecnicagonet.utils

sealed class ViewState <out T>{
    data object IDLE : ViewState<Nothing>()
    data class Success<T>(val data: T?) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
    data object Loading: ViewState<Nothing>()

}