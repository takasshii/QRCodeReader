package com.example.qrcodereader.domain

import java.io.Serializable

sealed interface QRCodeStringResult : Serializable {
    data class Url(
        val url: List<String>
    ) : QRCodeStringResult

    data class Text(
        val text: String
    ) : QRCodeStringResult
}
