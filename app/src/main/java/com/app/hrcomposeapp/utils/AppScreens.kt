package com.app.hrcomposeapp.utils

sealed class AppScreens(val title: String, val route: String) {
    object HomeScreen : AppScreens("Home", "homeScreen")
    object AddEditEmployeeScreen : AppScreens("Add/Edit Employee", "addEditEmployeeScreen")
    object EmployeeDetailScreen : AppScreens("Employee Details", "employeeDetailScreen")
    object Account : AppScreens("Account", "account")
    object Help : AppScreens("Help", "help")

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
