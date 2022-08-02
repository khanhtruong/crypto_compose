package com.khanhtruong.cryptocompose.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.composition.CurrencyItem
import com.khanhtruong.cryptocompose.ui.screen.Screen
import com.khanhtruong.cryptocompose.ui.theme.CryptoComposeTheme
import com.khanhtruong.cryptocompose.viewmodel.CurrenciesViewModel

@Preview
@Composable
fun HomeIndexScreenPreview() {
    val viewModel: CurrenciesViewModel = hiltViewModel()
    CryptoComposeTheme() {
        HomeIndexScreen(currenciesViewModel = viewModel) {}
    }
}

@Composable
fun HomeIndexScreen(
    currenciesViewModel: CurrenciesViewModel,
    navigate: (String) -> Unit = {},
) {
    val context = LocalContext.current

    val currencies = listOf(
        Currency(
            "btc",
            456962321113.0,
            23914.0,
            "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
        ),
        Currency(
            "eth",
            205498639817.0,
            1714.38,
            "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"
        ),
    )

    Column {
        // Build recycler list of currencies
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(currencies) {
                CurrencyItem(it) { currency ->
                    // onCardClicked
                    navigate.invoke(Screen.AssetDetails.withArgs(currency.name ?: ""))
                }
            }
        }
    }
}
