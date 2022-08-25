package com.app.hrcomposeapp.ui.theme.customWidget

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun CustomTextField(
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = remember {
        KeyboardOptions.Default
    },
    inputWrapper: String,
    @StringRes labelResId: Int,
) {
    var fieldValue by remember { mutableStateOf(inputWrapper) }

    Column {
        TextField(
            value = fieldValue,
            label = { Text(stringResource(labelResId)) },
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            onValueChange = { fieldValue = it })
    }
}