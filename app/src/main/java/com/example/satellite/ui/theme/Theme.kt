package com.example.satellite.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = BlueColor,
    secondary = BlueColor,
    tertiary = BlueColor,
    background = LightBackgroundColor,
    surface = LightSurfaceColor,
    onBackground = LightOnBackgroundColor,
    onSurface = LightOnSurfaceColor,
)

@Composable
fun SatelliteTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = colorScheme.background
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}