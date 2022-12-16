package com.example.qrcodereader.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodereader.data.args.ArgsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val argsRepository: ArgsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ResultUiState.INITIAL)
    val uiState: StateFlow<ResultUiState> = _state.asStateFlow()

    init {
        fetchArgs()
    }

    private fun fetchArgs() {
        // ローディング開始
        _state.value = _state.value.copy(proceeding = true)

        viewModelScope.launch {
            val args = argsRepository.getQRCodeResultArgs()
            // レスポンスに応じてLiveDataに値を格納
            _state.value = args.fold(
                onSuccess = {
                    _state.value.copy(
                        result = it
                    )
                },
                onFailure = {
                    _state.value.copy(error = it.toString())
                },
            )
            // ローディングを終了
            _state.value = _state.value.copy(proceeding = false)
        }
    }
}
