package com.app.hrcomposeapp.utils

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("homeScreen")
    object AddEditEmployeeScreen: AppScreens("addEditEmployeeScreen")
    object EmployeeDetailScreen: AppScreens("employeeDetailScreen")

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
