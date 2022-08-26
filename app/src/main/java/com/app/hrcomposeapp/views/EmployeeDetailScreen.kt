package com.app.hrcomposeapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.utils.AppConstants
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow

@Composable
fun EmployeeDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Employee Details", navController = navController)
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Virat Kohli (003) ",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Senior Software Engineer ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Having 6 years of experience",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "viratk1993@gmail.com | +919090908989",
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
                            onClick = { navController.navigate(AppConstants.ADD_EDIT_EMPLOYEE) },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Edit Details", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun EmployeeDetailScreen(){

}