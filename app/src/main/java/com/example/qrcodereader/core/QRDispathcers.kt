package com.example.qrcodereader.core

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val qrDispatcher: QRDispatchers)

enum class QRDispatchers {
    IO
}