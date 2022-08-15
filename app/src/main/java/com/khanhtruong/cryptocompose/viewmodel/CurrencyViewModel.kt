package com.khanhtruong.cryptocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.repository.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CurrencyUiState {
    data class Success(val currencies: List<Currency>) : CurrencyUiState()
    data class Error(val exception: Throwable) : CurrencyUiState()
    object Loading : CurrencyUiState()
}

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyRepo: CurrencyRepo) : ViewModel() {
    private val _uiState: MutableStateFlow<CurrencyUiState> =
        MutableStateFlow(CurrencyUiState.Loading)
    val uiState: StateFlow<CurrencyUiState> = _uiState

    init {
        viewModelScope.launch {
            currencyRepo.getCurrencies("usd")
                .catch { err ->
                    _uiState.value = CurrencyUiState.Error(err)
                }
                .collectLatest {
                    delay(1000L)
                    _uiState.value = CurrencyUiState.Success(it)
                }
        }
    }
}