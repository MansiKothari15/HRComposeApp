package com.app.hrcomposeapp.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun EmployeeDetailScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    empId: String?
) {

    homeViewModel.findEmployeeById(empId!!)
    Log.d(
        "TAG",
        "EmpId: $empId / EmployeeDetails: ${homeViewModel.foundEmployee.observeAsState().value}"
    )
    val selectedEmployee = homeViewModel.foundEmployee.observeAsState().value

    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Employee Details", navController = navController)
        }
    ) {
        if (selectedEmployee != null) {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    /*Image(
                        painterResource(id = R.drawable.sample_profile_pic),
                        contentDescription = null,
                        Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(50)),
                        contentScale = ContentScale.Crop
                    )*/
                    Text(
                        text = "Employee ID - ${selectedEmployee.employeeId} ",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Name - ${selectedEmployee.employeeName}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Designation - ${selectedEmployee.employeeDesignation}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Having ${selectedEmployee.empExperience} years of experience",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${selectedEmployee.empEmail} | ${selectedEmployee.empPhoneNo}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row() {
                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Delete Employee", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                navController.currentBackStackEntry?.arguments?.putSerializable(
                                    "employeeToEdit",
                                    selectedEmployee
                                )
                                navController.currentBackStackEntry?.arguments?.putBoolean(
                                    "isEdit",
                                    true
                                )
                                navController.navigate(AppScreens.AddEditEmployeeScreen.route)
                            },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Edit Details", color = Color.White)
                        }
                    }
                }
            }
        }

    }
}