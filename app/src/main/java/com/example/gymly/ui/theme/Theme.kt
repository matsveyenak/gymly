package com.example.gymly.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define your custom colors
val White = Color(0xFFFFFFFF) // Pure white
val BlueAccent = Color(0xFF007BFF) // Example blue accent color
val DarkGray = Color(0xFF333333) // for dark text
val LightGray = Color(0xFFEEEEEE) // for light background

// Define your custom color schemes
private val DarkColorScheme = darkColorScheme(
    primary = BlueAccent,
    secondary = BlueAccent,
    tertiary = BlueAccent,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = White,
    onSurface = White,
    surfaceVariant = White, // Use White as surfaceVariant
    onSurfaceVariant = DarkGray // Use DarkGray for onSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = BlueAccent,
    secondary = BlueAccent,
    tertiary = BlueAccent,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = DarkGray,
    onSurface = DarkGray,
    surfaceVariant = White, // Use White as surfaceVariant
    onSurfaceVariant = DarkGray // Use DarkGray for onSurfaceVariant
)

@Composable
fun GymlyTheme(
    darkTheme: Boolean = false, // Use system's dark theme by default
    dynamicColor: Boolean = false, // Disable dynamic colors
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}