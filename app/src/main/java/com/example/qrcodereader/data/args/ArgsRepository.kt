package com.example.qrcodereader.data.args

import com.example.qrcodereader.domain.QRCodeStringResult

interface ArgsRepository {
    suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult): ArgsResult<Unit>

    suspend fun getQRCodeResultArgs(): ArgsResult<QRCodeStringResult>
}

// 結果を返すクラス
sealed class ArgsResult<out T> {
    // 成功した場合
    data class Success<out T>(val data: T) : ArgsResult<T>()

    // 失敗した場合
    data class Error(val exception: Exception) : ArgsResult<Nothing>()
}
