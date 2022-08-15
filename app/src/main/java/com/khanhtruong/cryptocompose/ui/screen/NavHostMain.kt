package com.khanhtruong.cryptocompose.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khanhtruong.cryptocompose.ui.screen.details.AssetDetailsScreen
import com.khanhtruong.cryptocompose.ui.screen.home.HomeIndexScreen
import com.khanhtruong.cryptocompose.viewmodel.CurrencyViewModel
import com.khanhtruong.cryptocompose.viewmodel.SharedViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AssetDetails : Screen("asset_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Splash : HomeScreen("splash")
    object HomeIndex : HomeScreen("home/index")
}

sealed class AssetDetailsScreen(val route: String) {
    object AssetDetailsIndex : AssetDetailsScreen("asset_details/{currency_id}")
}

@Composable
fun NavHostMain(
    modifier: Modifier,
    navController: NavHostController,
    setTitle: (String) -> Unit
) {
    // -------------- Create shared view_model -------------- //
    // This view_model will always available between application
    // Because it's created under [MainScreenScaffold]
    val viewModel: SharedViewModel = hiltViewModel()

    // Construct base navigation graph
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Home.route,
    ) {
        // Declare root route
        // Home     ->  |Splash
        //              |HomeIndexScreen
        // Settings ->  |Profile
        //              |Settings
        addHomeScreens(
            navController = navController,
            viewModel = viewModel,
        )

        addAssetScreens(
            navController = navController,
            viewModel = viewModel,
            setTitle = setTitle,
        )
    }
}

// Inspect child route or it could be another root
// HomeIndex    ->  |RandomScreen
private fun NavGraphBuilder.addHomeScreens(
    navController: NavHostController,
    viewModel: SharedViewModel,
) {
    navigation(
        route = Screen.Home.route,
        startDestination = HomeScreen.HomeIndex.route,
    ) {
        addSplashScreen(
            navController = navController,
        )
        addHomeIndexScreen(
            navController = navController,
            viewModel = viewModel,
        )
    }
}

// Inspect child route or it could be another root
// AssetDetails    ->  |AssetDetailsScreen
private fun NavGraphBuilder.addAssetScreens(
    navController: NavHostController,
    viewModel: SharedViewModel,
    setTitle: (String) -> Unit,
) {
    navigation(
        route = Screen.AssetDetails.route,
        startDestination = AssetDetailsScreen.AssetDetailsIndex.route,
    ) {
        addAssetDetailsScreen(
            navController = navController,
            viewModel = viewModel,
            setTitle = setTitle,
        )
    }
}

// Declare HomeIndexScreen compose with a navigate CallBack
private fun NavGraphBuilder.addHomeIndexScreen(
    navController: NavHostController,
    viewModel: SharedViewModel,
) {
    composable(route = HomeScreen.HomeIndex.route) {
        val currencyViewModel: CurrencyViewModel = hiltViewModel()

        HomeIndexScreen(viewModel, currencyViewModel) { route ->
            navController.navigate(route)
        }
    }
}

// Declare SplashScreen compose with a navigate CallBack
private fun NavGraphBuilder.addSplashScreen(
    navController: NavHostController,
) {
    composable(route = HomeScreen.HomeIndex.route) {
        Text(text = "Splash Screen")
    }
}

// Declare AssetDetailsScreen compose with a navigate CallBack
private fun NavGraphBuilder.addAssetDetailsScreen(
    navController: NavHostController,
    viewModel: SharedViewModel,
    setTitle: (String) -> Unit
) {
    composable(
        route = AssetDetailsScreen.AssetDetailsIndex.route,
        arguments = listOf(
            navArgument(name = "currency_id") {
                nullable = false
                defaultValue = ""
                type = NavType.StringType
            }
        ),
    ) {
        viewModel.currency.value?.name?.let { name ->
            setTitle(name.uppercase())
        }
        AssetDetailsScreen(viewModel) { screen ->
            navController.navigate(screen.route)
        }
    }
}
