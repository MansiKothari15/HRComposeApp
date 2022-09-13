package com.app.hrcomposeapp.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Employee
import com.app.hrcomposeapp.ui.theme.Shapes
import com.app.hrcomposeapp.ui.theme.customWidget.CustomTextField
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.viewmodels.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var empPhoneNumber: String = ""
var empEmailId: String = ""
var empExp: String = ""
var empDesignation: String = ""
var empId: String = ""
var empName: String = ""

@Composable
fun AddEditEmployeeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    employeeToEdit: String?,
    isEdit: Boolean
) {
    lateinit var selectedEmployee: Employee
    val mContext = LocalContext.current
    // The coroutine scope for event handlers calling suspend functions.
    val coroutineScope = rememberCoroutineScope()
    // True if the message about the edit feature is shown.
    var validationMessageShown by remember { mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    clearAll()
    if (isEdit) {
        homeViewModel.findEmployeeById(employeeToEdit!!)
        selectedEmployee = homeViewModel.foundEmployee.observeAsState().value!!
        empId = selectedEmployee.employeeId.toString()
        empName = selectedEmployee.employeeName
        empDesignation = selectedEmployee.employeeDesignation
        empExp = selectedEmployee.empExperience.toString()
        empEmailId = selectedEmployee.empEmail
        empPhoneNumber = selectedEmployee.empPhoneNo.toString()
    }

    // Shows the validation message.
    suspend fun showEditMessage() {
        if (!validationMessageShown) {
            validationMessageShown = true
            delay(3000L)
            validationMessageShown = false
        }
    }

    val scrollState = rememberScrollState()

    var isEdited by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(
                title = if (isEdit) "Edit Employee" else "Add Employee",
                navController = navController
            )
        },
        content = {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(state = scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_person_pin_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(
                            colorResource(id = R.color.primaryColor),
                        ),
                        modifier = Modifier
                            .size(140.dp)
                            .clickable { galleryLauncher.launch("image/*") }
                            .clip(RoundedCornerShape(50)),
                    )
                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(mContext.contentResolver, it)

                        } else {
                            val source = ImageDecoder
                                .createSource(mContext.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(Shapes.large),
                            )
                        }
                    }
                    ValidationMessage(validationMessageShown)

                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_name,
                        inputWrapper = empName,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empName = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_id,
                        inputWrapper = empId,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 5,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empId = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_designation,
                        inputWrapper = empDesignation,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empDesignation = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.emp_exp,
                        inputWrapper = empExp,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 3,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empExp = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.email_id,
                        inputWrapper = empEmailId,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empEmailId = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.phone_no,
                        inputWrapper = empPhoneNumber,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done,
                        ),
                        maxLength = 10,
                        maxLines = 1
                    ) {
                        isEdited = true
                        empPhoneNumber = it
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        if (isEdited) {
                            val employee = Employee(
                                id = if (isEdit) selectedEmployee.id else empId.trim().toInt(),
                                employeeId = empId.trim().toLong(),
                                employeeName = empName,
                                employeeDesignation = empDesignation,
                                empExperience = empExp.toFloat(),
                                empEmail = empEmailId,
                                empPhoneNo = empPhoneNumber.toLong()
                            )
                            if (isEdit) {
                                updateEmployeeInDB(mContext, navController, employee, homeViewModel)
                            } else {
                                addEmployeeInDB(mContext, navController, employee, homeViewModel)
                            }
                            clearAll()
                        } else {
//                            toast(mContext, "Please add or update something...")
                            coroutineScope.launch {
                                showEditMessage()
                            }
                        }
                    }) {
                        Text(
                            text = if (isEdit) "Update Details" else "Add",
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    )
}

/**
 * Shows a validation message.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ValidationMessage(shown: Boolean) {
    AnimatedVisibility(
        visible = shown,
        enter = slideInVertically(
            // Enters by sliding in from offset -fullHeight to 0.
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding out from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = "Please add or update detail",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun clearAll() {
    empId = ""
    empName = ""
    empDesignation = ""
    empExp = ""
    empEmailId = ""
    empPhoneNumber = ""
}

fun addEmployeeInDB(
    context: Context,
    navController: NavHostController,
    employee: Employee,
    homeViewModel: HomeViewModel
) {
    homeViewModel.addEmployee(employee)
    navController.popBackStack()
}

fun updateEmployeeInDB(
    context: Context,
    navController: NavHostController,
    employee: Employee,
    homeViewModel: HomeViewModel
) {
    homeViewModel.updateEmployeeDetails(employee)
    navController.popBackStack()
}

