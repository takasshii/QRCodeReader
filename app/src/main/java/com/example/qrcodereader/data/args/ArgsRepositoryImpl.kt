package com.example.qrcodereader.data.args

import com.example.qrcodereader.core.Dispatcher
import com.example.qrcodereader.core.QRDispatchers
import com.example.qrcodereader.domain.QRCodeStringResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArgsRepositoryImpl @Inject constructor(
    @Dispatcher(QRDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ArgsRepository {

    private val latestArgsMutex = Mutex()

    private var latestArgs: QRCodeStringResult? = null

    override suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult): Result<Unit> =
        withContext(ioDispatcher) {
            try {
                latestArgsMutex.withLock {
                    latestArgs = qrCodeStringResult
                }
                Result.success(Unit)
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }

    override suspend fun getQRCodeResultArgs(): Result<QRCodeStringResult> =
        withContext(Dispatchers.IO) {
            try {
                val args = requireNotNull(latestArgs) {
                    "引数が正しくセットされていません"
                }
                Result.success(args)
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
}
