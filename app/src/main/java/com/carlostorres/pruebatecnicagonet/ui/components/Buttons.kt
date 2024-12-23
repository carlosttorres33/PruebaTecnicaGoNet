package com.carlostorres.pruebatecnicagonet.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
    buttonColor: Color = Color.Black,
    textColor: Color = Color.White,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        onClick = { onClick() },
        enabled = enabled
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = icon,
                contentDescription = ""
            )
        }
        Text(text = buttonText, color = textColor)
    }

}