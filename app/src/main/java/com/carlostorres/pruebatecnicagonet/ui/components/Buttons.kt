package com.carlostorres.pruebatecnicagonet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carlostorres.pruebatecnicagonet.ui.theme.AppBg
import com.carlostorres.pruebatecnicagonet.utils.bounceClick

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {

    Button(
        modifier = modifier
            .bounceClick(enabled)
            .background(AppBg, RoundedCornerShape(5.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(5.dp),
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
        Text(text = buttonText, color = Color.White, fontWeight = FontWeight.Bold)
    }

}