package com.khanhtruong.cryptocompose.ui.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.khanhtruong.cryptocompose.R
import com.khanhtruong.cryptocompose.ui.composition.CustomSnackbarHost
import com.khanhtruong.cryptocompose.ui.composition.NavigationIcon

@Composable
fun MainScreen() {
    MainScreenScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenScaffold() {
    val context = LocalContext.current
    val state = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val baseTitle = ""
    val (title, setTitle) = remember { mutableStateOf(baseTitle) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            setTitle(getAppBarTitleBy(backStackEntry.destination.route, context))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { CustomSnackbarHost(state) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = { NavigationIcon(navController) },
            )
        },
        content = { innerPadding ->
            NavHostMain(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                setTitle = setTitle,
            )
        }
    )
}

fun getAppBarTitleBy(route: String?, context: Context): String {
    return when (route) {
        null -> ""
        "spash" -> "Splash"
        HomeScreen.HomeIndex.route -> context.getString(R.string.home_screen_title)
        AssetDetailsScreen.AssetDetailsIndex.route -> context.getString(R.string.asset_details_screen_title)
        else -> "Unknow page"
    }
}