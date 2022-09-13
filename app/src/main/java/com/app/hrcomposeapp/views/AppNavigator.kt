package com.app.hrcomposeapp.views

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun AppRouter(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit
) {
    NavHost(navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController, homeViewModel, openDrawer)
        }
        composable(route = AppScreens.AddEditEmployeeScreen.route + "/{empId}/{isEdit}",
            arguments = listOf(
                navArgument("empId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )) {
            val isEdit = it.arguments?.getBoolean("isEdit")
            val empId = it.arguments?.getString("empId")
            EnterAnimation {
                AddEditEmployeeScreen(navController, homeViewModel, empId, isEdit!!)
            }
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
            EnterAnimation {
                EmployeeDetailScreen(navController, homeViewModel, empId)
            }
        }
        composable(route = AppScreens.Account.route) {
            AccountScreen(navController, homeViewModel, openDrawer)
        }
        composable(route = AppScreens.Help.route) {
            HelpScreen(navController, homeViewModel, openDrawer)
        }
        composable(route = AppScreens.Contact.route) {
            ContactUsScreen(navController, homeViewModel, openDrawer)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    val visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f,
            animationSpec = tween(500, 500)
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        content()
    }
}
