package com.example.qrcodereader.ui

import androidx.compose.runtime.Composable
import com.example.qrcodereader.navigation.QRNavHost

@Composable
fun QRCodeApp(
    appState: QRCodeState = rememberQRCodeState(),
) {
    QRNavHost(navController = appState.navController)
}
