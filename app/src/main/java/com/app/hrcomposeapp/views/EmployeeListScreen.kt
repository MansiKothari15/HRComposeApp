package com.app.hrcomposeapp.views

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
) {
    homeViewModel.getAllEmployees()
    var visible1 by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            CustomToolbar(title = stringResource(id = R.string.app_name))
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
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AnimatedVisibility(
                        visible = visible1,
                        enter = fadeIn(
                            // customize with tween AnimationSpec
                            animationSpec = tween(
                                durationMillis = 5000,
                                delayMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                        // you can also add animationSpec in fadeOut if need be.
                        exit = fadeOut() + shrinkHorizontally(),

                        ) {
                        Text(
                            "Sadly, there are no employees in your company.",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = visible1,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn() + expandIn(expandFrom = Alignment.TopStart),
                // Scale down from the TopLeft by setting TransformOrigin to (0f, 0f), while shrinking
                // the layout towards Top start and fading. This will create a coherent look as if the
                // scale is impacting the layout size.
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                ExtendedFloatingActionButton(
                    text = {
                        Text(text = stringResource(id = R.string.add_employee))
                    },
                    onClick = {
                        navController.navigate(AppScreens.AddEditEmployeeScreen.route + "/" + "0" + "/" + false)
                    },
                    modifier = Modifier.padding(0.dp),
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_add_24),
                            contentDescription = stringResource(id = R.string.desc_add_fab),
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.Black,
                )

            }
        }
    )
}

@Composable
fun EmployeeCard(employee: Employee, navController: NavHostController) {
    val expanded by remember { mutableStateOf(true) }
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
                .clickable {
                    navController.navigate(
                        AppScreens.EmployeeDetailScreen.routeWithArgs(
                            employee.employeeId.toString()
                        )
                    )
                }
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_person_pin_24),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        colorResource(id = R.color.primaryColor),
                    ),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50)),
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = employee.employeeName,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (expanded) {
                        Text(
                            text = employee.employeeDesignation,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}