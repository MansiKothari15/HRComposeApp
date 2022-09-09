package com.app.hrcomposeapp.views

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun AccountScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomToolbar(title = "Account", openDrawer)
        }
    ) {

    }
}