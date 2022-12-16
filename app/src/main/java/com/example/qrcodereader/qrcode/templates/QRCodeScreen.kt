package com.example.qrcodereader.qrcode.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun QRCodeScreen(
    onButtonClick: () -> Unit,
    showDialog: Boolean,
    dismissDialog: () -> Unit,
    exception: String,
    retry: () -> Unit,
    proceeding: Boolean,
    modifier: Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (proceeding) {
            TextButton(onClick = { onButtonClick() }) {
                Text(text = "QRCodeReaderを起動する")
            }
        }

        if (showDialog) {
            Dialog(
                dismissDialog = dismissDialog,
                exception = exception,
                retry = retry
            )
        }

    }
}

@Composable
fun Dialog(
    dismissDialog: () -> Unit,
    exception: String,
    retry: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            dismissDialog()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    dismissDialog()
                    retry()
                }
            ) {
                Text("リトライ")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dismissDialog()
                }
            ) {
                Text("閉じる")
            }
        },
        title = {
            Text("エラーが発生しました。")
        },
        text = {
            Text(exception)
        },
    )
}
