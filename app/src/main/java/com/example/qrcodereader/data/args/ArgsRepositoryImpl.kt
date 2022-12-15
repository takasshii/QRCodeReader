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

    override suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult): ArgsResult<Unit> =
        withContext(ioDispatcher) {
            try {
                latestArgsMutex.withLock {
                    latestArgs = qrCodeStringResult
                }
                ArgsResult.Success(data = Unit)
            } catch (exception: Exception) {
                ArgsResult.Error(exception = exception)
            }
        }

    override suspend fun getQRCodeResultArgs(): ArgsResult<QRCodeStringResult> =
        withContext(Dispatchers.IO) {
            try {
                val args = requireNotNull(latestArgs) {
                    "引数が正しくセットされていません"
                }
                ArgsResult.Success(data = args)
            } catch (exception: Exception) {
                ArgsResult.Error(exception = exception)
            }
        }
}
