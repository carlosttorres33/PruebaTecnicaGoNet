package com.carlostorres.pruebatecnicagonet.login.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.carlostorres.pruebatecnicagonet.R
import com.carlostorres.pruebatecnicagonet.login.presentation.LoginEvents
import com.carlostorres.pruebatecnicagonet.login.presentation.LoginViewModel
import com.carlostorres.pruebatecnicagonet.ui.components.DefaultButton
import com.carlostorres.pruebatecnicagonet.ui.components.DefaultTextField
import com.carlostorres.pruebatecnicagonet.ui.components.PasswordTextField
import com.carlostorres.pruebatecnicagonet.ui.components.dialogs.ErrorDialog
import com.carlostorres.pruebatecnicagonet.ui.components.dialogs.LoadingDialog
import com.carlostorres.pruebatecnicagonet.ui.theme.AppBg
import com.carlostorres.pruebatecnicagonet.ui.theme.BgBlue
import com.carlostorres.pruebatecnicagonet.ui.theme.BgPurple
import com.carlostorres.pruebatecnicagonet.utils.ViewState
import com.carlostorres.pruebatecnicagonet.utils.decodeSampledBitmapFromResource

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = state.viewState) {
        if (state.viewState is ViewState.Success) {
            onLoginSuccess()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = BgPurple
    ) { padding ->

        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            val (
                bgImage,
                appTitle,
                loginForm
            ) = createRefs()

            val horizontalGuideLine = createGuidelineFromTop(0.75f)

            AnimatedContent(targetState = state.viewState, label = "") { dialog ->
                when (dialog) {
                    is ViewState.Error -> {
                        ErrorDialog(message = dialog.message) {
                            viewModel.updateViewState(ViewState.IDLE)
                        }
                    }

                    ViewState.Loading -> {
                        LoadingDialog()
                    }

                    else -> {}
                }
            }

            Box(
                modifier = Modifier
                    .constrainAs(bgImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(horizontalGuideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .background(AppBg),
            )

            Text(
                modifier = Modifier
                    .constrainAs(appTitle){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(loginForm.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    },
                textAlign = TextAlign.Center,
                text = "Prueba Técnica GoNet",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
            )

            Card(
                modifier = Modifier.constrainAs(loginForm) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Login Form"
                    )

                    DefaultTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.username,
                        onValueChange = {
                            viewModel.onEvent(LoginEvents.UsernameChanged(it))
                        },
                        icon = Icons.Outlined.Person,
                        labelText = "Username"
                    )

                    DefaultTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.email,
                        icon = Icons.Outlined.Email,
                        onValueChange = {
                            viewModel.onEvent(LoginEvents.EmailChanged(it))
                        },
                        labelText = "Email"
                    )

                    PasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = state.password,
                        onValueChange = {
                            viewModel.onEvent(LoginEvents.PasswordChanged(it))
                        },
                        labelText = "Password"
                    )

                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = (state.username.isNotEmpty() && state.email.isNotEmpty() && state.password.isNotEmpty()),
                        buttonText = "LOGIN",
                        onClick = {
                            viewModel.onEvent(LoginEvents.Login)
                        }
                    )

                }
            }

        }

    }
}