package com.example.qrcodereader.data.dataStore

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.qrcodereader.QRCodeStringResultPreference
import com.example.qrcodereader.core.Dispatcher
import com.example.qrcodereader.core.QRDispatchers
import com.example.qrcodereader.domain.QRCodeStringResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArgsRepositoryImpl @Inject constructor(
    @Dispatcher(QRDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val argsDataStore : DataStore<QRCodeStringResultPreference>,
) : ArgsRepository {
    override suspend fun writeQRCodeResultArgs(qrCodeStringResult: QRCodeStringResult) {
        withContext(ioDispatcher) {
            try {
                argsDataStore.updateData { currentArgs ->
                    when (qrCodeStringResult) {
                        is QRCodeStringResult.Url -> {
                            currentArgs.toBuilder().clearUrl().addAllUrl(qrCodeStringResult.url)
                                .build()
                        }
                        is QRCodeStringResult.Text -> {
                            currentArgs.toBuilder().clearText().setText(qrCodeStringResult.text)
                                .build()
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.e("QRCodeResultArgs", "Failed to update user preferences")
            }
        }
    }

    override suspend fun getQRCodeResultArgs(): ArgsResult<QRCodeStringResultPreference> =
        withContext(Dispatchers.IO) {
            try {
                ArgsResult.Success(data = argsDataStore.data.first())
            } catch (exception: Exception) {
                Log.e("QRCodeResultArgs", "Failed to update user preferences")
                ArgsResult.Error(exception = exception)
            }
        }
}
