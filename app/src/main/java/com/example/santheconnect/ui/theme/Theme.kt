package com.example.santheconnect.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,
    secondary = SecondaryPurple,
    onSecondary = Color.White,
    tertiary = AccentGreen,
    onTertiary = Color.White,
    background = BackgroundCream,
    onBackground = TextPrimary,
    surface = SurfaceWhite,
    onSurface = TextPrimary,
    surfaceVariant = HighlightYellow,
    onSurfaceVariant = HighlightBrown,
    secondaryContainer = SoftGreen,
    onSecondaryContainer = SuccessGreen,
    error = ErrorRed,
    onError = Color.White,
    outline = TextSecondary.copy(alpha = 0.5f)
)

private val DarkColors = darkColorScheme(
    primary = PrimaryOrange,
    onPrimary = Color.White,
    secondary = SecondaryPurple,
    onSecondary = Color.White,
    tertiary = AccentGreen,
    onTertiary = Color.White,
    background = TextPrimary,
    onBackground = BackgroundCream,
    surface = Color(0xFF1E1E1E),
    onSurface = BackgroundCream,
    surfaceVariant = Color(0xFF2D1B12),
    onSurfaceVariant = HighlightYellow,
    secondaryContainer = Color(0xFF166534).copy(alpha = 0.2f),
    onSecondaryContainer = SoftGreen,
    error = ErrorRed,
    onError = Color.White,
    outline = TextSecondary
)

@Composable
fun SantheConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
