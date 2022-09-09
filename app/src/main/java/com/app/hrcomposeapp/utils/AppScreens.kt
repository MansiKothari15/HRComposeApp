package com.app.hrcomposeapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val title: String, val route: String, var icon: ImageVector) {
    object HomeScreen : AppScreens("Home", "homeScreen", Icons.Default.Home)
    object AddEditEmployeeScreen : AppScreens("Add/Edit Employee", "addEditEmployeeScreen", Icons.Default.Home)
    object EmployeeDetailScreen : AppScreens("Employee Details", "employeeDetailScreen", Icons.Default.Home)
    object Account : AppScreens("Account", "account", Icons.Default.AccountCircle)
    object Help : AppScreens("Help", "help", Icons.Default.Info)

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
