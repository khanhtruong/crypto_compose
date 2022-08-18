package com.khanhtruong.cryptocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.repository.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        refreshCurrencyList(true)
    }

    private fun refreshCurrencyList(refresh: Boolean) {
        currencyRepo.getCurrencies(refresh, "usd")
            .catch { err ->
                _uiState.value = CurrencyUiState.Error(err)
            }
            .onEach { currencies ->
                _uiState.value = CurrencyUiState.Success(currencies)
            }.onCompletion {
                _isRefreshing.value = false
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        _isRefreshing.value = true
        _uiState.value = CurrencyUiState.Loading
        refreshCurrencyList(true)
    }
}