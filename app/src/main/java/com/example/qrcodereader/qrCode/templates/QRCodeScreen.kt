package com.example.qrcodereader.qrCode.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QRCodeScreen(
    onButtonClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        TextButton(onClick = { onButtonClick() }) {
            Text(text = "QRCodeReaderを起動する")
        }
    }
}
