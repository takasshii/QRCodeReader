package com.example.qrcodereader.qrCode

sealed interface QRCodeEvent {
    data class StringResult(
        val stringResult: String
    ) : QRCodeEvent

    data class UnknownExpectedError(
        val errorMessage: String,
    ) : QRCodeEvent
}
