package com.example.qrcodereader.qrcode

import android.media.Image
import com.example.qrcodereader.domain.QRCodeStringResult

data class QRCodeUiState(
    val stringResult: QRCodeStringResult?,
    val image: Image?,
    val error: String,
    val events: List<Event>,
    val proceeding: Boolean
) {
    companion object {
        val INITIAL = QRCodeUiState(
            stringResult = null,
            image = null,
            error = "",
            events = emptyList(),
            proceeding = false,
        )
    }

    sealed interface Event {
        object Success : Event
        data class Error(val message: String) : Event
        data class NextPage(val stringResult: QRCodeStringResult) : Event
    }
}
