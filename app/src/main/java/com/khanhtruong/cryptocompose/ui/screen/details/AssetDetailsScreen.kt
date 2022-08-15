package com.khanhtruong.cryptocompose.ui.screen.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khanhtruong.cryptocompose.model.Currency
import com.khanhtruong.cryptocompose.ui.composition.GradientCard
import com.khanhtruong.cryptocompose.ui.screen.Screen
import com.khanhtruong.cryptocompose.ui.theme.CryptoComposeTheme
import com.khanhtruong.cryptocompose.ui.theme.spacing
import com.khanhtruong.cryptocompose.viewmodel.SharedViewModel
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
@Preview
fun PreviewAssetDetailsScreen() {
    val viewModel: SharedViewModel = hiltViewModel()
    viewModel.setCurrency(Currency())
    CryptoComposeTheme {
        AssetDetailsScreen(viewModel)
    }
}

@Composable
fun AssetDetailsScreen(
    viewModel: SharedViewModel,
    navigate: (Screen) -> Unit = {},
) {
    val currency = viewModel.currency.value

    if (currency != null) {
        ChartDetails(currency)
    } else {
        Text(text = "Empty data")
    }
}

@Composable
fun ChartDetails(currency: Currency) {
    GradientCard(
        data = "",
        onCardClick = null
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .padding(top = MaterialTheme.spacing.medium),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large)
            ) {
                Column() {
                    Text(
                        text = currency.name ?: "",
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "$${currency.currentPrice ?: ""}",
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column() {
                    Text(
                        text = "0.0",
                        modifier = Modifier.align(Alignment.End),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = currency.symbol?.uppercase() ?: "",
                        modifier = Modifier.align(Alignment.End),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            LineChartView()
        }
    }
}

@Composable
fun LineChartView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(end = 36.dp)
    ) {
        LineChart(
            lineChartData = LineChartData(
                points = listOf(
                    LineChartData.Point(1f, "Line 1"),
                    LineChartData.Point(10f, "Line 2"),
                    LineChartData.Point(5f, "Line 3"),
                    LineChartData.Point(20f, "Line 4"),
                    LineChartData.Point(15f, "Line 5"),
                    LineChartData.Point(30f, "Line 6"),
                    LineChartData.Point(20f, "Line 7")
                )
            ),
            // Optional properties.
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            animation = simpleChartAnimation(),
            pointDrawer = FilledCircularPointDrawer(
                color = Color.White,
            ),
            lineDrawer = SolidLineDrawer(
                color = Color.White,
            ),
            xAxisDrawer = emptyXAxisDrawer(),
            yAxisDrawer = emptyYAxisDrawer(),
            horizontalOffset = 5f,
        )
    }
}

private fun emptyXAxisDrawer(): SimpleXAxisDrawer {
    return SimpleXAxisDrawer(
        labelTextSize = 0.sp,
        labelTextColor = Color.Transparent,
        drawLabelEvery = 1,// draw label text every $drawLabelEvery, like 1, 2, 3 and so on.
        axisLineThickness = 0.dp,
        axisLineColor = Color.Transparent,
    )
}

private fun emptyYAxisDrawer(): SimpleYAxisDrawer {
    return SimpleYAxisDrawer(
        labelTextSize = 2.sp,
        labelTextColor = Color.Transparent,
        drawLabelEvery = 1,
        labelValueFormatter = { _ -> "" },
        axisLineThickness = 0.dp,
        axisLineColor = Color.Transparent,
    )
}