package com.example.qrcodereader.result.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qrcodereader.result.ResultViewModel
import com.example.qrcodereader.result.templates.ResultScreen

@Composable
fun ResultRoute(
    viewModel: ResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    ResultScreen(
        stringResult = uiState.result,
        proceeding = uiState.proceeding,
    )
}
