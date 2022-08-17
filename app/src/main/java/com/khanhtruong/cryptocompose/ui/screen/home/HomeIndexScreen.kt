package com.khanhtruong.cryptocompose.ui.screen.home

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
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
    val isRefreshing = currencyViewModel.isRefreshing.collectAsState().value

    when (uiState) {
        is CurrencyUiState.Success -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { currencyViewModel.refresh() },
            ) {
                BuildCurrencyList(currencies = uiState.currencies) { currency ->
                    // onCardClicked
                    sharedViewModel.setCurrency(currency)
                    navigate.invoke(Screen.AssetDetails.withArgs(currency.name ?: ""))
                }
            }
        }
        is CurrencyUiState.Error -> {
            ErrorView(uiState.exception) {
                currencyViewModel.refresh()
            }
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
fun ErrorView(exception: Throwable, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            Icons.Default.Warning,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = exception.message ?: "",
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = onRetry,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Tap to retry",
            )
        }
    }
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
