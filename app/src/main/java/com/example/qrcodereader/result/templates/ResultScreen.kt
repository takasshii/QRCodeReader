package com.example.qrcodereader.result.templates

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.qrcodereader.domain.QRCodeStringResult

@Composable
fun ResultScreen(
    stringResult: QRCodeStringResult,
    popBackStack: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (stringResult) {
            is QRCodeStringResult.Text -> {
                Text(text = stringResult.text)
            }
            is QRCodeStringResult.Url -> {
                WebViewWidget(url = stringResult.url)
            }
        }
    }
}

@Composable
fun WebViewWidget(
    url: List<String>
) {
    AndroidView(
        factory = {
            WebView(it)
        },
        update = { webView ->
            webView.webViewClient = WebViewClient()
            url.forEach {
                webView.loadUrl(it)
            }
        }
    )
}
