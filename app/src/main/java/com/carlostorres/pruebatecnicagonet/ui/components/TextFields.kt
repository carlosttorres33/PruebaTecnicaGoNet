package com.carlostorres.pruebatecnicagonet.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.HideSource
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    icon: ImageVector = Icons.Default.Lock,
    keyboardType: KeyboardType = KeyboardType.Password,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {

    DefaultTextField(
        modifier = modifier,
        value = value,
        onValueChange = {onValueChange(it)},
        labelText = labelText ,
        icon = icon,
        keyboardType = keyboardType,
        enabled = enabled,
        keyboardActions = keyboardActions,
        isPassword = true
    )

}

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
    isPassword: Boolean = false
) {

    var hidePassword by remember {
        mutableStateOf(true)
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = labelText)
        },
        leadingIcon = {
            if (icon != null){
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.Black
                )
            } else null
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        ),
        maxLines = 1,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        enabled = enabled,
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = { hidePassword = !hidePassword },
                    enabled = enabled
                ) {
                    Icon(
                        imageVector = if (hidePassword) Icons.Rounded.RemoveRedEye else Icons.Rounded.Password,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        },
        visualTransformation = if (isPassword && hidePassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}