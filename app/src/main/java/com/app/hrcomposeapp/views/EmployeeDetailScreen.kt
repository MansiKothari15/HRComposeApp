package com.app.hrcomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.utils.toast
import com.app.hrcomposeapp.viewmodels.HomeViewModel

@Composable
fun EmployeeDetailScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    empId: String?
) {

    val openDialog = remember { mutableStateOf(false)  }

    homeViewModel.findEmployeeById(empId!!)

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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_person_pin_24),
                        contentDescription = null,
                        Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(50)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "#${selectedEmployee.employeeId} - ${selectedEmployee.employeeName}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "(${selectedEmployee.employeeDesignation})",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Having ${selectedEmployee.empExperience} years of experience",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${selectedEmployee.empEmail} | ${selectedEmployee.empPhoneNo}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Button(
                            onClick = {
                                openDialog.value = true
                            },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "Delete Employee", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                navController.navigate(AppScreens.AddEditEmployeeScreen.route + "/" + selectedEmployee.employeeId + "/" + true)
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
        if(openDialog.value){
            CustomAlertDialog(
                alertMsg = "Are you sure you want to delete this employee?",
                homeViewModel = homeViewModel,
                navController = navController,
                selectedEmployee = selectedEmployee!!
            )
        }
    }

}

@Composable
fun CustomAlertDialog(
    alertMsg: String,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    selectedEmployee: Employee,
    modifier: Modifier = Modifier
){
    val mContext = LocalContext.current
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            //openDialog.value = false
        },
        title = { Text("Alert") },
        text = { Text(alertMsg) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { }) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                homeViewModel.deleteEmployee(selectedEmployee)
                toast(mContext, "Employee Deleted Successfully !!!")
                navController.popBackStack()

            }) {
                Text(text = "Yes")
            }
        }
    )
}