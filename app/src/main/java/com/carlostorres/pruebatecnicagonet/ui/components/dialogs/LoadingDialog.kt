package com.carlostorres.pruebatecnicagonet.ui.components.dialogs

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        CircularProgressIndicator(
            color = Color.Black
        )
    }
}