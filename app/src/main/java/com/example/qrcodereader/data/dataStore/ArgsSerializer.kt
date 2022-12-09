package com.example.qrcodereader.data.dataStore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.qrcodereader.QRCodeStringResultPreference
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject


class ArgsSerializer @Inject constructor() : Serializer<QRCodeStringResultPreference> {
    override val defaultValue: QRCodeStringResultPreference =
        QRCodeStringResultPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): QRCodeStringResultPreference {
        try {
            return QRCodeStringResultPreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: QRCodeStringResultPreference,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.argsDataStore: DataStore<QRCodeStringResultPreference> by dataStore(
    fileName = "args.pb",
    serializer = ArgsSerializer()
)
