package com.app.hrcomposeapp.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.hrcomposeapp.utils.AppConstants
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun AppRouter(homeViewModel: HomeViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppConstants.HOME_SCREEN) {
        composable(route = AppConstants.HOME_SCREEN) {
            HomeScreen(navController, homeViewModel)
        }
        composable(route = AppConstants.ADD_EDIT_EMPLOYEE) {
            AddEditEmployeeScreen(navController, homeViewModel)
        }
        composable(route = AppConstants.EMPLOYEE_DETAIL) {
            EmployeeDetailScreen(navController)
        }
    }

}