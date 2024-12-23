package com.carlostorres.pruebatecnicagonet.login.data.remote.repository

import android.content.SharedPreferences
import com.carlostorres.pruebatecnicagonet.login.data.remote.LoginService
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginRequest
import com.carlostorres.pruebatecnicagonet.login.data.remote.model.LoginResponse
import com.carlostorres.pruebatecnicagonet.login.domain.repository.LoginRepo
import com.google.gson.Gson
import retrofit2.Response

class LoginRepoImpl (
    private val loginService: LoginService,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : LoginRepo {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return loginService.login(loginRequest)
    }

    override fun saveLogin(loginResponse: LoginResponse) {
        val json = gson.toJson(loginResponse)
        sharedPreferences.edit().putString("LOGIN_RESPONSE", json).apply()
    }

    override fun getLogin(): LoginResponse? {
        val json = sharedPreferences.getString("LOGIN_RESPONSE", null)
        return json?.let { gson.fromJson(it, LoginResponse::class.java) }
    }

}