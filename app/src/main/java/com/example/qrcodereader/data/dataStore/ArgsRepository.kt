package com.example.qrcodereader.data.dataStore

import com.example.qrcodereader.QRCodeStringResultPreference
import com.example.qrcodereader.domain.QRCodeStringResult

interface ArgsRepository {
    suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult)

    suspend fun getQRCodeResultArgs(): ArgsResult<QRCodeStringResultPreference>
}

// 結果を返すクラス
sealed class ArgsResult<out R> {
    // 成功した場合
    data class Success<out T>(val data: T) : ArgsResult<T>()

    // 失敗した場合
    data class Error(val exception: Exception) : ArgsResult<Nothing>()
}
