package com.app.hrcomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun AppMainScreen(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        /*navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }*/
                    }
                )
            }
        ) {
            AppRouter(homeViewModel = homeViewModel, openDrawer = {
                openDrawer()
            })
        }
    }
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_person_pin_24),
                colorFilter = ColorFilter.tint(
                    colorResource(id = R.color.primaryColor),
                ),
                contentDescription = "App icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(50)),
            )
        }
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.route,
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .clickable {
                        onDestinationClicked(screen.route)
                    }
                    .padding(start = 10.dp)
            )
        }
    }
}

private val screens = listOf(
    AppScreens.HomeScreen,
    AppScreens.AddEditEmployeeScreen,
    AppScreens.EmployeeDetailScreen
)
