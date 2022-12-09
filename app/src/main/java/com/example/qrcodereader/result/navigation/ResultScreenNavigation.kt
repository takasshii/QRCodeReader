package com.example.qrcodereader.result.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.qrcodereader.result.route.ResultRoute

const val resultNavigationRoute = "result_route"

fun NavController.navigateToResult(navOptions: NavOptions? = null) {
    this.navigate(resultNavigationRoute, navOptions)
}

fun NavGraphBuilder.resultScreen(
    popBackStack : () -> Unit,
) {
    composable(
        route = resultNavigationRoute
    ) {
        ResultRoute(
            popBackStack = popBackStack
        )
    }
}
