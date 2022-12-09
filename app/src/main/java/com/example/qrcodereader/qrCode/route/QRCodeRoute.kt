package com.example.qrcodereader.qrCode.route

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qrcodereader.qrCode.QRCodeEvent
import com.example.qrcodereader.qrCode.QRCodeUiState
import com.example.qrcodereader.qrCode.QRCodeViewModel
import com.example.qrcodereader.qrCode.templates.QRCodeScreen
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun QRCodeRoute(
    navigateToResult: () -> Unit,
    viewModel: QRCodeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(ScanContract()) {
        if (it.contents != null) {
            viewModel.dispatch(
                QRCodeEvent.StringResult(stringResult = it.contents)
            )
        } else {
            viewModel.dispatch(
                QRCodeEvent.UnknownExpectedError(errorMessage = "エラーが発生しました。")
            )
        }
    }

    fun onButtonClick() {
        val options = ScanOptions().setOrientationLocked(true)
        launcher.launch(options)
    }

    QRCodeScreen(
        onButtonClick = { onButtonClick() },
    )

    LaunchedEffect(key1 = uiState.events) {
        if (uiState.events.firstOrNull() != null) {
            when (val event = uiState.events.firstOrNull()) {
                is QRCodeUiState.Event.Success -> {
                    // イベントを消費
                    viewModel.consumeEvent(event)
                    uiState.stringResult?.let {
                        viewModel.nextPage(it)
                    }
                }
                is QRCodeUiState.Event.Error -> {
                    // イベントを消費
                    viewModel.consumeEvent(event)
                }
                is QRCodeUiState.Event.NextPage -> {
                    Log.v("Debug", "NextPage")
                    navigateToResult()
                    viewModel.pushArgs(event.stringResult)
                    // イベントを消費
                    viewModel.consumeEvent(event)
                }
                else -> {
                    Log.d("TAG", "else pattern")
                }
            }
        }
    }
}
