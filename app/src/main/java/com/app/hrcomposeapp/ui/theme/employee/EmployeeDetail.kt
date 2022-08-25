/*
package com.app.hrcomposeapp.ui.theme.employee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun SimpleFilledTextFieldSample() {
    var employeeName by remember { mutableStateOf("Mansi Kothari") }
    var employeeID by remember { mutableStateOf("14078") }
    var experience by remember { mutableStateOf("7 Yrs") }
    var technology by remember { mutableStateOf("Android | Flutter") }

    Column(modifier = Modifier.background(Color.Gray)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.transparent)),
            value = employeeName,
            textStyle = LocalTextStyle.current.copy(
                color = colorResource(id = R.color.white),
            ),
            onValueChange = { employeeName = it },
            label = { Text("Employee Name", color = Color.White) }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.transparent)),
            textStyle = LocalTextStyle.current.copy(
                color = colorResource(id = R.color.white),
            ),
            value = employeeID,
            onValueChange = { employeeID = it },
            label = { Text("Employee ID", color = Color.White) }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.transparent)),
            textStyle = LocalTextStyle.current.copy(
                color = colorResource(id = R.color.white),
            ),
            value = experience,
            onValueChange = { experience = it },
            label = { Text("Experience", color = Color.White) }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.transparent)),
            textStyle = LocalTextStyle.current.copy(
                color = colorResource(id = R.color.white),
            ),
            value = technology,
            onValueChange = { technology = it },
            label = { Text("Technology", color = Color.White) }
        )
    }

}*/
