package com.app.hrcomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,

    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    onSecondary = ColorOnPrimary
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,

    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    onSecondary = ColorOnPrimary

)

@Composable
fun HRComposeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                      content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}