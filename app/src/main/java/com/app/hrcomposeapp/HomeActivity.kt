package com.app.hrcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.hrcomposeapp.AppConstants.ADD_EDIT_EMPLOYEE
import com.app.hrcomposeapp.AppConstants.EMPLOYEE_DETAIL
import com.app.hrcomposeapp.AppConstants.HOME_SCREEN
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.ui.theme.HRComposeAppTheme
import com.app.hrcomposeapp.ui.theme.customWidget.CustomTextField
import dagger.hilt.android.AndroidEntryPoint

val NewEmployee: Employee = Employee(
    employeeName = "Virat Kohli",
    employeeDesignation = "Run Machine",
    empExperience = 14,
    empEmail = "viratk@gmail.com",
    empPhoneNo = 9876543210
)

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            HRComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppRouter(homeViewModel, this@HomeActivity)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getAllEmployees()
    }

}


@Composable
fun AppRouter(homeViewModel: HomeViewModel, homeActivity: HomeActivity) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HOME_SCREEN) {
        composable(route = HOME_SCREEN) {
            HomeScreen(navController, homeViewModel, homeActivity)
        }
        composable(route = ADD_EDIT_EMPLOYEE) {
            AddEditEmployeeScreen(navController, homeViewModel)
        }
        composable(route = EMPLOYEE_DETAIL) {
            EmployeeDetailScreen(navController)
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    homeActivity: HomeActivity
) {
//    val employeeList = listOf(
//        "Rohit Sharma", "KL Rahul", "Virat Kohli",
//        "Hardik Pandya", "Dinesh Karthik", "Jasprit Bumrah",
//        "Rohit Sharma", "KL Rahul", "Virat Kohli",
//        "Hardik Pandya", "Dinesh Karthik", "Jasprit Bumrah"
//    )


    Scaffold(
        topBar = {
            CustomToolbar(title = "HR Compose App")
        },
        content = {
            val employeeList: List<Employee> by homeViewModel.employeeList.observeAsState(initial = listOf())
            if (employeeList.isNotEmpty()) {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                        items(items = employeeList) { empName ->
                            EmployeeCard(name = empName.employeeName, navController = navController)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ADD_EDIT_EMPLOYEE) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun EmployeeCard(name: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    EMPLOYEE_DETAIL
                )
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        Text(
            text = name,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(20.dp)
        )
    }
}

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
                    )
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
                    )
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
                    )
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
                    )
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
                    )
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
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        addEmployeeInDB(navController, homeViewModel)
                    }) {
                        Text(text = "Add Employee", fontSize = 20.sp)
                    }
                }
            }
        }
    )
}

fun addEmployeeInDB(navController: NavHostController, homeViewModel: HomeViewModel) {
    homeViewModel.addEmployee(
        NewEmployee
    )
    navController.popBackStack()
}

@Composable
fun EmployeeDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Employee Details", navController = navController)
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {}
        }
    )
}

@Composable
fun CustomToolbarWithBackArrow(title: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    "contentDescription",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun CustomToolbar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
    )
}