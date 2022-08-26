package com.app.hrcomposeapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.ui.theme.customWidget.CustomTextField
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.viewmodels.HomeViewModel

var empPhoneNumber: String = ""
var empEmailId: String = ""
var empExp: String = ""
var empDesignation: String = ""
var empId: String = ""
var empName: String = ""

@Composable
fun AddEditEmployeeScreen(navController: NavHostController, homeViewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Add/Edit Employee", navController = navController)
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_name,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                    ) { empName = it }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_id,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    ) { empId = it }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_designation,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                    ) { empDesignation = it }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_exp,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    ) { empExp = it }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.email_id,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                    ) { empEmailId = it }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.phone_no,
                        inputWrapper = "",
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                    ) { empPhoneNumber = it }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        val employee = Employee(
                            employeeId = empId.toLong(),
                            employeeName = empName,
                            employeeDesignation = empDesignation,
                            empExperience = empExp.toInt(),
                            empEmail = empEmailId,
                            empPhoneNo = empPhoneNumber.toLong()
                        )

                        addEmployeeInDB(navController, employee, homeViewModel)
                    }) {
                        Text(text = "Add Employee", fontSize = 20.sp)
                    }
                }
            }
        }
    )
}

fun addEmployeeInDB(
    navController: NavHostController,
    employee: Employee,
    homeViewModel: HomeViewModel
) {
    homeViewModel.addEmployee(employee)
    navController.popBackStack()
}