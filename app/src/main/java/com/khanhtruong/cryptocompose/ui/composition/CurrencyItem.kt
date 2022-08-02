package com.khanhtruong.cryptocompose.ui.composition

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.theme.GradientColorEnd
import com.khanhtruong.cryptocompose.ui.theme.GradientColorStart
import com.khanhtruong.cryptocompose.ui.theme.defaultCardColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyItem(currency: Currency, cardClick: (Currency) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(bottom = 8.dp)),
        colors = defaultCardColors(),
        onClick = {
            cardClick(currency)
        }
    ) {
        // Apply gradient colors
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            GradientColorStart,
                            GradientColorEnd
                        )
                    )
                )
        ) {

            // -------------------- CONTENT ------------------- //
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {

                // ------------------- ASSET ICON -------------------- //
                Image(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.secondary),
                    painter = rememberImagePainter(currency.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(8.dp))

                // ------------------- ASSET DETAILS -------------------- //
                Row(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = currency.name?.uppercase() ?: "",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
