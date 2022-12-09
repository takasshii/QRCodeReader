package com.example.qrcodereader.qrCode.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qrcodereader.qrCode.route.QRCodeRoute

const val qrCodeGraphRoutePattern = "qr_code_graph"
const val qrCodeNavigationRoute = "qr_code_navigation"

fun NavController.navigateToQRCodeGraph(navOptions: NavOptions? = null) {
    this.navigate(qrCodeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.qrCodeGraph(
    navigateToResult: () -> Unit,
) {
    navigation(
        route = qrCodeGraphRoutePattern,
        startDestination = qrCodeNavigationRoute
    ) {
        composable(route = qrCodeNavigationRoute) {
            QRCodeRoute(
                navigateToResult = navigateToResult,
            )
        }
    }
}
