package com.carlostorres.pruebatecnicagonet.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.carlostorres.pruebatecnicagonet.ui.navigation.NavigationManager
import com.carlostorres.pruebatecnicagonet.ui.theme.PruebaTecnicaGonetTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaGonetTheme {
                NavigationManager(isLogged = isLoggedIn())
            }
        }
    }

    private fun isLoggedIn() : Boolean = viewModel.loginResponse.value != null

}