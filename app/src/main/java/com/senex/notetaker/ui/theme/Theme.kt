package com.senex.notetaker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun NoteTakerTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    rememberSystemUiController().setSystemBarsColor(Color.Transparent)

    MaterialTheme(
        content = content,
        colorScheme = if (useDarkTheme) darkColors else lightColors,
    )
}

private val darkColors = darkColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,
    surface = GraySurface,
    primaryContainer = PrimaryOrange,
    onPrimaryContainer = Color.White,
    onSecondaryContainer = GrayOnSurface,
    secondary = PrimaryOrange,
    onSecondary = Color.White,
    error = PrimaryOrange,
)

private val lightColors = lightColorScheme(
    primary = Red700,
    onPrimary = Color.White,
    secondary = Red700,
    onSecondary = Color.White,
    error = Red800,
)
