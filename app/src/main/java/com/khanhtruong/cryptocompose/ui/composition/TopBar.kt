package com.khanhtruong.cryptocompose.ui.composition

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavigationIcon(navController: NavController) {
    navController.previousBackStackEntry?.let {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Up button")
        }
    }
}
