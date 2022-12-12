package com.example.qrcodereader.qrcode

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodereader.data.dataStore.ArgsRepository
import com.example.qrcodereader.domain.QRCodeStringResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(
    private val argsRepository: ArgsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(QRCodeUiState.INITIAL)
    val uiState: StateFlow<QRCodeUiState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dispatch(
            QRCodeEvent.UnknownExpectedError(
                errorMessage = throwable.toString(),
            ),
        )
    }

    private val scope: CoroutineScope
        get() = viewModelScope + exceptionHandler

    fun dispatch(event: QRCodeEvent) {
        scope.launch {
            updateState(event)
            Log.v("Debug", "For Debug ${uiState.value}")
        }
    }

    private fun updateState(event: QRCodeEvent) {
        _state.value = when (event) {
            is QRCodeEvent.StringResult -> {
                val result = decode(event.stringResult)
                // ViewModelイベント発行
                val newEvents = _state.value.events.plus(QRCodeUiState.Event.Success)
                _state.value.copy(
                    events = newEvents,
                    stringResult = result
                )
            }
            is QRCodeEvent.UnknownExpectedError -> {
                // ViewModelイベント発行
                val newEvents =
                    _state.value.events.plus(QRCodeUiState.Event.Error(event.errorMessage))
                // 値をセット
                _state.value.copy(events = newEvents)
            }
            else -> {
                _state.value.copy(
                    error = "対応していないファイル形式です"
                )
            }
        }
    }

    private fun decode(result: String): QRCodeStringResult {
        // URLかどうかを判定
        val regex = "(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+"
        val urls = regex.toRegex(RegexOption.IGNORE_CASE).findAll(result).map { it.value }.toList()

        return if (urls.isNotEmpty()) {
            QRCodeStringResult.Url(url = urls)
        } else {
            QRCodeStringResult.Text(text = result)
        }
    }

    // イベントを消費する関数
    fun consumeEvent(event: QRCodeUiState.Event) {
        val newEvents = _state.value.events.filterNot { it == event }
        _state.value = _state.value.copy(events = newEvents)
    }

    // 次のページに進むEventを発行する関数
    fun nextPage(stringResult: QRCodeStringResult) {
        val newEvents = _state.value.events.plus(QRCodeUiState.Event.NextPage(stringResult))
        _state.value = _state.value.copy(events = newEvents)
    }

    fun pushArgs(args: QRCodeStringResult) {
        viewModelScope.launch {
            argsRepository.writeQRCodeResultArgs(args)
        }
    }
}
