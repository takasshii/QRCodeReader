package com.example.qrcodereader.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberQRCodeState(
    navController: NavHostController = rememberNavController()
) : QRCodeState {
    return remember(navController) {
        QRCodeState(navController)
    }
}

@Stable
class QRCodeState(
    val navController: NavHostController
) {
    // ここで拡張関数を定義
}