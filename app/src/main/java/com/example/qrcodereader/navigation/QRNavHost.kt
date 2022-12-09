package com.example.qrcodereader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.qrcodereader.qrCode.navigation.qrCodeGraph
import com.example.qrcodereader.qrCode.navigation.qrCodeGraphRoutePattern
import com.example.qrcodereader.result.navigation.navigateToResult
import com.example.qrcodereader.result.navigation.resultScreen

@Composable
fun QRNavHost(
    navController: NavHostController,
    startDestination: String = qrCodeGraphRoutePattern,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        qrCodeGraph(
            navigateToResult = {
                navController.navigateToResult()
            }
        )
        resultScreen()
    }
}
