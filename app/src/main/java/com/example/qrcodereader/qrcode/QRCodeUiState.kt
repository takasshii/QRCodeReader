package com.example.qrcodereader.qrcode

import android.media.Image
import com.example.qrcodereader.domain.QRCodeStringResult

data class QRCodeUiState(
    val proceeding : Boolean,
    val stringResult: QRCodeStringResult?,
    val image: Image?,
    val error: String,
    val events: List<Event>,
) {
    companion object {
        val INITIAL = QRCodeUiState(
            proceeding = false,
            stringResult = null,
            image = null,
            error = "",
            events = emptyList(),
        )
    }

    sealed interface Event {
        object Success : Event
        data class Error(val message: String) : Event
        data class NextPage(val stringResult: QRCodeStringResult) : Event
    }
}
