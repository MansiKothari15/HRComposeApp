package com.app.hrcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.hrcomposeapp.AppConstants.ADD_EDIT_EMPLOYEE
import com.app.hrcomposeapp.AppConstants.EMPLOYEE_DETAIL
import com.app.hrcomposeapp.AppConstants.HOME_SCREEN
import com.app.hrcomposeapp.ui.theme.HRComposeAppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HRComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppRouter()
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HOME_SCREEN) {
        composable(route = HOME_SCREEN) {
            HomeScreen(navController)
        }
        composable(route = ADD_EDIT_EMPLOYEE) {
            AddEditEmployeeScreen(navController)
        }
        composable(route = EMPLOYEE_DETAIL) {
            EmployeeDetailScreen(navController)
        }
    }
    //HomeScreen(navController = navController)
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val employeeList = listOf(
        "Rohit Sharma", "KL Rahul", "Virat Kohli",
        "Hardik Pandya", "Dinesh Karthik", "Jasprit Bumrah",
        "Rohit Sharma", "KL Rahul", "Virat Kohli",
        "Hardik Pandya", "Dinesh Karthik", "Jasprit Bumrah"
    )
    Scaffold(
        topBar = {
            CustomToolbar(title = "HR Compose App")
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                    items(items = employeeList) { empName ->
                        EmployeeCard(name = empName, navController = navController)
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
fun AddEditEmployeeScreen(navController: NavHostController) {
    val empName = remember { mutableStateOf(TextFieldValue("")) }
    val empDesignation = remember { mutableStateOf(TextFieldValue("")) }
    val empExperience = remember { mutableStateOf(TextFieldValue("")) }
    val empCtc = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Add/Edit Employee", navController = navController)
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = "",
                        placeholder = { Text(text = "Employee Name") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                        ),
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        onValueChange = {})
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(value = "",
                        placeholder = { Text(text = "Employee Designation") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                        ),
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        onValueChange = {})
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(value = "",
                        placeholder = { Text(text = "Employee Experience (in years)") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                        ),
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        onValueChange = {})
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(value = "",
                        placeholder = { Text(text = "Employee CTC (in Rupees)") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                        ),
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        onValueChange = {})
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { navController.popBackStack() }) {
                        Text(text = "Add Employee", fontSize = 20.sp)
                    }
                }
            }
        }
    )
}

@Composable
fun EmployeeDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(title = "Employee Details", navController = navController)
        },
        content = {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {

            }
        }
    )
}

@Composable
fun CustomToolbarWithBackArrow(title: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { navController.popBackStack() }) {
                // below line is use to
                // specify navigation icon.
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