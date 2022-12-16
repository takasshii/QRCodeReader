package com.example.qrcodereader.data.args

import com.example.qrcodereader.domain.QRCodeStringResult

interface ArgsRepository {
    suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult): Result<Unit>

    suspend fun getQRCodeResultArgs(): Result<QRCodeStringResult>
}
