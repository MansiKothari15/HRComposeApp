package com.app.hrcomposeapp.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navArgument
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
) {
    Scaffold(
        topBar = {
            CustomToolbar(title = "HR Compose App")
        },
        content = {
            val employeeList: List<Employee> by homeViewModel.employeeList.observeAsState(initial = listOf())
            if (employeeList.isNotEmpty()) {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                        items(items = employeeList) { emp ->
                            EmployeeCard(employee = emp, navController = navController)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppScreens.AddEditEmployeeScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun EmployeeCard(employee: Employee, navController: NavHostController) {
    var expanded by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .clickable { navController.navigate(AppScreens.EmployeeDetailScreen.routeWithArgs(
                    employee.employeeId.toString()
                )) }
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row {
                /*Image(
                    painterResource(id = R.drawable.sample_profile_pic),
                    contentDescription = null,
                    Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50)),
                    contentScale = ContentScale.Crop
                )*/
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = employee.employeeName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (expanded) {
                        Text(
                            text = employee.employeeDesignation,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Serif,
                            color = Color.Gray,
                        )
                    }
                }
            }
        }
    }
}