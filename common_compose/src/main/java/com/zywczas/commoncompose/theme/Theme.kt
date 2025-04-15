package com.zywczas.commoncompose.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Theme {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalRippleConfiguration provides RippleConfiguration(Color.PrimaryLight)
        ) {
            Box(
                Modifier
                    .background(Color.ScreenBackground)
                    .fillMaxSize()
            ) {
                MaterialTheme(
                    colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
                    typography = typography,
                    content = content
                )
            }
        }
    }

    @Composable
    fun Preview(content: @Composable () -> Unit) {
        App(content = content)
    }
}

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme(
    primary = Color.PrimaryLight,
    // bottom sheet
    surfaceContainerLow = Color.ScreenBackground,
    surface = Color.PrimaryLight.copy(alpha = 0.5f),
    onBackground = Color.BlueText
)

private val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 22.sp,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 24.sp,
    ),
    // toolbar
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 24.sp,
        color = Color.BaseText,
    )
)
