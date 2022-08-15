package com.khanhtruong.cryptocompose.ui.screen.home

import android.widget.ProgressBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.composition.CurrencyItem
import com.khanhtruong.cryptocompose.ui.screen.Screen
import com.khanhtruong.cryptocompose.ui.theme.CryptoComposeTheme
import com.khanhtruong.cryptocompose.viewmodel.CurrencyUiState
import com.khanhtruong.cryptocompose.viewmodel.CurrencyViewModel
import com.khanhtruong.cryptocompose.viewmodel.SharedViewModel
import java.lang.Exception

@Preview
@Composable
fun HomeIndexScreenPreview() {
    val viewModel: SharedViewModel = hiltViewModel()
    val currencyViewModel: CurrencyViewModel = hiltViewModel()

    CryptoComposeTheme() {
        HomeIndexScreen(sharedViewModel = viewModel, currencyViewModel = currencyViewModel) {}
    }
}

@Composable
fun HomeIndexScreen(
    sharedViewModel: SharedViewModel,
    currencyViewModel: CurrencyViewModel,
    navigate: (String) -> Unit = {},
) {
    val uiState = currencyViewModel.uiState.collectAsState().value
    when (uiState) {
        is CurrencyUiState.Success -> {
            BuildCurrencyList(currencies = uiState.currencies) { currency ->
                // onCardClicked
                sharedViewModel.setCurrency(currency)
                navigate.invoke(Screen.AssetDetails.withArgs(currency.name ?: ""))
            }
        }
        is CurrencyUiState.Error -> {
            ErrorView(uiState.exception)
        }
        is CurrencyUiState.Loading -> {
            LoadingView()
        }
    }
}

@Composable
fun BuildCurrencyList(currencies: List<Currency>, onCardClick: (Currency) -> Unit) {
    Column {
        // Build recycler list of currencies
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(currencies) {
                CurrencyItem(it, onCardClick)
            }
        }
    }
}

@Composable
fun ErrorView(exception: Throwable) {
    Text(
        text = exception.message ?: "",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun LoadingView() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
