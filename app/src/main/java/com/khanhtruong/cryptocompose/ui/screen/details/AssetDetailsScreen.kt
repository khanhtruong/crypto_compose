package com.khanhtruong.cryptocompose.ui.screen.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.screen.Screen

@Composable
fun AssetDetailsScreen(
    setTitle: (String) -> Unit,
    currencyID: String,
    navigate: (Screen) -> Unit = {},
) {
    setTitle(currencyID.uppercase())
    Text(text = currencyID)
}