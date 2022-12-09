package com.example.qrcodereader.result

import com.example.qrcodereader.domain.QRCodeStringResult

data class ResultUiState(
    val result: QRCodeStringResult,
    val error: String,
    val proceeding: Boolean,
) {
    companion object {
        val INITIAL = ResultUiState(
            result = QRCodeStringResult.Text("何も読み取れませんでした。やり直してください。"),
            error = "",
            proceeding = false,
        )
    }
}