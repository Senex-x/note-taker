package com.senex.notetaker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun NoteTakerTheme(useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colorScheme = if (useDarkTheme) darkColors else lightColors,
    )
}

private val darkColors = darkColorScheme(
    primary = Red300,
    onPrimary = Color.White,
    secondary = Red300,
    onSecondary = Color.White,
    error = Red200,
)

private val lightColors = lightColorScheme(
    primary = Red700,
    onPrimary = Color.White,
    secondary = Red700,
    onSecondary = Color.White,
    error = Red800,
)
