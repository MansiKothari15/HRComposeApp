package com.app.hrcomposeapp.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun AppRouter(homeViewModel: HomeViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(route = AppScreens.AddEditEmployeeScreen.route + "/{employeeToEdit}/{isEdit}",
            arguments = listOf(
                navArgument("employeeToEdit") {
                    type = NavType.SerializableType(Employee::class.java)
                    nullable = true
                },
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )) {
            val isEdit = navController.previousBackStackEntry?.arguments?.getBoolean("isEdit")
            val employeeToEdit = navController.previousBackStackEntry?.arguments?.getSerializable("employeeToEdit") as Employee?
            AddEditEmployeeScreen(navController, homeViewModel,employeeToEdit,isEdit)
        }
        composable(route = AppScreens.EmployeeDetailScreen.route + "/{empId}",
            arguments = listOf(
                navArgument("empId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {
            val empId = it.arguments?.getString("empId")
            EmployeeDetailScreen(navController, homeViewModel, empId)
        }
    }

}