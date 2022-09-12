package com.app.hrcomposeapp.views

import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.ui.theme.customWidget.CustomTextField
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.HomeViewModel


@Composable
fun ContactUsScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    openDrawer: () -> Unit
) {
    val context = LocalContext.current
    var userMessage: String = ""
    var emailSubject = stringResource(id = R.string.customer_feedback)

    Scaffold(
        topBar = {
            CustomToolbar(title = "Raise a Concern", openDrawer)
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
                        .padding(10.dp),
                ) {
                    Text(
                        "Have any concern or feedback? Share with us.",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .height(130.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.contact_query,
                        inputWrapper = empExp,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        maxLength = 150,
                        maxLines = 5
                    ) {
                        userMessage = it
                    }
                    Button(
                        onClick = {
                            val addresses = arrayOf("abc@gmail.com")
                            composeEmail(context,addresses, emailSubject, userMessage)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Send",
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    )
}

fun composeEmail(context: Context, addresses: Array<String>, subject: String, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
    }
    startActivity(context,createChooser(intent, "Send an email..."),null)
}

@Preview
@Composable
fun PreviewContactUsScreen(){

}