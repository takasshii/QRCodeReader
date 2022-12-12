package com.example.qrcodereader.qrcode.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QRCodeScreen(
    modifier: Modifier,
    onButtonClick: () -> Unit,
    proceeding: Boolean,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (proceeding) {
            Text(text = "書き込み中です。")
        } else {
            TextButton(onClick = { onButtonClick() }) {
                Text(text = "QRCodeReaderを起動する")
            }
        }
    }
}
