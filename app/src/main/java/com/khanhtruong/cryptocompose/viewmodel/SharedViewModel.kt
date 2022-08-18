package com.khanhtruong.cryptocompose.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.khanhtruong.cryptocompose.model.Currency

class SharedViewModel : ViewModel() {
    var currency = mutableStateOf<Currency?>(null)
        private set

    fun setCurrency(currency: Currency) {
        Log.d("Debuggg", currency.toString())
        this.currency.value = currency
    }
}