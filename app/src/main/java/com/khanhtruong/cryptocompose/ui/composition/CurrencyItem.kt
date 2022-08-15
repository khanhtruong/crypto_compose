package com.khanhtruong.cryptocompose.ui.composition

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.theme.LocalSpacing
import com.khanhtruong.cryptocompose.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyItem(currency: Currency, cardClick: (Currency) -> Unit) {

    // Apply gradient colors
    GradientCard(
        data = currency,
        onCardClick = cardClick
    ) {
        // -------------------- CONTENT ------------------- //
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = MaterialTheme.spacing.small)
        ) {

            // ------------------- ASSET ICON -------------------- //
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .height(MaterialTheme.spacing.large)
                    .aspectRatio(1f),
                model = rememberAsyncImagePainter(currency.image),
//                    painter = rememberImagePainter(currency.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            // ------------------- ASSET DETAILS -------------------- //
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // ------------------- ASSET NAME ------------------- //
                Row {
                    Text(
                        text = currency.symbol?.uppercase() ?: "",
                        style = MaterialTheme
                            .typography
                            .labelLarge
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                        modifier = Modifier.padding(LocalSpacing.current.default)
                    )

                    Text(
                        text = currency.name?.capitalize(Locale.current) ?: "",
                        style = MaterialTheme
                            .typography
                            .labelMedium
                            .copy(
                                color = MaterialTheme.colorScheme.onSecondary,
                            ),
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(start = MaterialTheme.spacing.extraSmall, bottom = 1.dp),
                    )
                }

                // ------------------- ASSET PRICE ------------------- //
                currency.currentPrice?.let { price ->
                    Text(
                        text = "$$price",
                        style = MaterialTheme
                            .typography
                            .labelMedium
                            .copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                            ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

