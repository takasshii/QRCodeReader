package com.example.qrcodereader.qrcode

sealed interface QRCodeEvent {
    data class StringResult(
        val stringResult: String
    ) : QRCodeEvent

    data class UnknownExpectedError(
        val errorMessage: String,
    ) : QRCodeEvent
}
