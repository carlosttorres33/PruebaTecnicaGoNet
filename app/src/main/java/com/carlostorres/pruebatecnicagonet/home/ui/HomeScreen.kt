package com.carlostorres.pruebatecnicagonet.home.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.carlostorres.pruebatecnicagonet.R
import com.carlostorres.pruebatecnicagonet.home.presentation.HomeEvents
import com.carlostorres.pruebatecnicagonet.home.presentation.HomeViewModel
import com.carlostorres.pruebatecnicagonet.ui.components.DefaultButton
import com.carlostorres.pruebatecnicagonet.ui.components.ShowDataTextField
import com.carlostorres.pruebatecnicagonet.ui.components.dialogs.ErrorDialog
import com.carlostorres.pruebatecnicagonet.ui.components.dialogs.LoadingDialog
import com.carlostorres.pruebatecnicagonet.ui.theme.AppBg
import com.carlostorres.pruebatecnicagonet.ui.theme.BgPurple
import com.carlostorres.pruebatecnicagonet.utils.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {

    val state = viewModel.state

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = BgPurple
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBg)
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            AnimatedContent(targetState = state.viewState, label = "") { viewState ->
                when (viewState) {
                    ViewState.Loading -> {
                        LoadingDialog()
                    }

                    is ViewState.Success -> {
                        Card(
                            Modifier
                                .padding(18.dp)
                                .fillMaxSize(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                item {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = "Profile Info:",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                    )
                                }

                                item {
                                    if (state.imageUrl != null) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .animateContentSize()
                                                .size(150.dp)
                                                .clip(RoundedCornerShape(12.dp)),
                                            model = state.imageUrl,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            onState = {
                                                when (it) {
                                                    AsyncImagePainter.State.Empty -> TODO()
                                                    is AsyncImagePainter.State.Error -> TODO()
                                                    is AsyncImagePainter.State.Loading -> {
                                                        R.drawable.ic_launcher_foreground
                                                    }

                                                    is AsyncImagePainter.State.Success -> {
                                                        state.imageUrl
                                                    }
                                                }
                                            }
                                        )
                                    }
                                }

                                item {
                                    ShowDataTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = viewState.data?.id ?: "",
                                        onValueChange = {},
                                        label = "ID"
                                    )
                                }

                                item {
                                    ShowDataTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = viewState.data?.name ?: "",
                                        onValueChange = {},
                                        label = "Name"
                                    )
                                }

                                item {
                                    ShowDataTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = viewState.data?.lastName ?: "",
                                        onValueChange = {},
                                        label = "Last Name"
                                    )
                                }

                                item {
                                    ShowDataTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = viewState.data?.gender ?: "",
                                        onValueChange = {},
                                        label = "Gander"
                                    )
                                }

                                item {
                                    ShowDataTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        value = viewState.data?.age.toString() ?: "",
                                        onValueChange = {},
                                        label = "AGE"
                                    )
                                }

                                item {
                                    DefaultButton(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        icon = Icons.AutoMirrored.Filled.Logout,
                                        buttonText = "LOGOUT",
                                        onClick = {
                                            viewModel.onEvent(HomeEvents.OnLogout)
                                            onLogout()
                                        }
                                    )
                                }

                            }
                        }
                    }

                    is ViewState.Error -> {
                        ErrorDialog(message = viewState.message, textButton = "Logout") {
                            viewModel.onEvent(HomeEvents.OnLogout)
                        }
                    }

                    else -> {}
                }

            }
        }

    }
}