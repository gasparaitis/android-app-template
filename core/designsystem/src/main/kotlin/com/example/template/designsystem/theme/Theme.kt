/*
 * Copyright 2025 Replace With Your Name
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.template.designsystem.theme

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode

private val lightScheme =
    lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight,
        inversePrimary = inversePrimaryLight,
        surfaceDim = surfaceDimLight,
        surfaceBright = surfaceBrightLight,
        surfaceContainerLowest = surfaceContainerLowestLight,
        surfaceContainerLow = surfaceContainerLowLight,
        surfaceContainer = surfaceContainerLight,
        surfaceContainerHigh = surfaceContainerHighLight,
        surfaceContainerHighest = surfaceContainerHighestLight,
    )

private val darkScheme =
    darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
        surfaceDim = surfaceDimDark,
        surfaceBright = surfaceBrightDark,
        surfaceContainerLowest = surfaceContainerLowestDark,
        surfaceContainerLow = surfaceContainerLowDark,
        surfaceContainer = surfaceContainerDark,
        surfaceContainerHigh = surfaceContainerHighDark,
        surfaceContainerHighest = surfaceContainerHighestDark,
    )

private val mediumContrastLightColorScheme =
    lightColorScheme(
        primary = primaryLightMediumContrast,
        onPrimary = onPrimaryLightMediumContrast,
        primaryContainer = primaryContainerLightMediumContrast,
        onPrimaryContainer = onPrimaryContainerLightMediumContrast,
        secondary = secondaryLightMediumContrast,
        onSecondary = onSecondaryLightMediumContrast,
        secondaryContainer = secondaryContainerLightMediumContrast,
        onSecondaryContainer = onSecondaryContainerLightMediumContrast,
        tertiary = tertiaryLightMediumContrast,
        onTertiary = onTertiaryLightMediumContrast,
        tertiaryContainer = tertiaryContainerLightMediumContrast,
        onTertiaryContainer = onTertiaryContainerLightMediumContrast,
        error = errorLightMediumContrast,
        onError = onErrorLightMediumContrast,
        errorContainer = errorContainerLightMediumContrast,
        onErrorContainer = onErrorContainerLightMediumContrast,
        background = backgroundLightMediumContrast,
        onBackground = onBackgroundLightMediumContrast,
        surface = surfaceLightMediumContrast,
        onSurface = onSurfaceLightMediumContrast,
        surfaceVariant = surfaceVariantLightMediumContrast,
        onSurfaceVariant = onSurfaceVariantLightMediumContrast,
        outline = outlineLightMediumContrast,
        outlineVariant = outlineVariantLightMediumContrast,
        scrim = scrimLightMediumContrast,
        inverseSurface = inverseSurfaceLightMediumContrast,
        inverseOnSurface = inverseOnSurfaceLightMediumContrast,
        inversePrimary = inversePrimaryLightMediumContrast,
        surfaceDim = surfaceDimLightMediumContrast,
        surfaceBright = surfaceBrightLightMediumContrast,
        surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
        surfaceContainerLow = surfaceContainerLowLightMediumContrast,
        surfaceContainer = surfaceContainerLightMediumContrast,
        surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
        surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
    )

private val highContrastLightColorScheme =
    lightColorScheme(
        primary = primaryLightHighContrast,
        onPrimary = onPrimaryLightHighContrast,
        primaryContainer = primaryContainerLightHighContrast,
        onPrimaryContainer = onPrimaryContainerLightHighContrast,
        secondary = secondaryLightHighContrast,
        onSecondary = onSecondaryLightHighContrast,
        secondaryContainer = secondaryContainerLightHighContrast,
        onSecondaryContainer = onSecondaryContainerLightHighContrast,
        tertiary = tertiaryLightHighContrast,
        onTertiary = onTertiaryLightHighContrast,
        tertiaryContainer = tertiaryContainerLightHighContrast,
        onTertiaryContainer = onTertiaryContainerLightHighContrast,
        error = errorLightHighContrast,
        onError = onErrorLightHighContrast,
        errorContainer = errorContainerLightHighContrast,
        onErrorContainer = onErrorContainerLightHighContrast,
        background = backgroundLightHighContrast,
        onBackground = onBackgroundLightHighContrast,
        surface = surfaceLightHighContrast,
        onSurface = onSurfaceLightHighContrast,
        surfaceVariant = surfaceVariantLightHighContrast,
        onSurfaceVariant = onSurfaceVariantLightHighContrast,
        outline = outlineLightHighContrast,
        outlineVariant = outlineVariantLightHighContrast,
        scrim = scrimLightHighContrast,
        inverseSurface = inverseSurfaceLightHighContrast,
        inverseOnSurface = inverseOnSurfaceLightHighContrast,
        inversePrimary = inversePrimaryLightHighContrast,
        surfaceDim = surfaceDimLightHighContrast,
        surfaceBright = surfaceBrightLightHighContrast,
        surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
        surfaceContainerLow = surfaceContainerLowLightHighContrast,
        surfaceContainer = surfaceContainerLightHighContrast,
        surfaceContainerHigh = surfaceContainerHighLightHighContrast,
        surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
    )

private val mediumContrastDarkColorScheme =
    darkColorScheme(
        primary = primaryDarkMediumContrast,
        onPrimary = onPrimaryDarkMediumContrast,
        primaryContainer = primaryContainerDarkMediumContrast,
        onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
        secondary = secondaryDarkMediumContrast,
        onSecondary = onSecondaryDarkMediumContrast,
        secondaryContainer = secondaryContainerDarkMediumContrast,
        onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
        tertiary = tertiaryDarkMediumContrast,
        onTertiary = onTertiaryDarkMediumContrast,
        tertiaryContainer = tertiaryContainerDarkMediumContrast,
        onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
        error = errorDarkMediumContrast,
        onError = onErrorDarkMediumContrast,
        errorContainer = errorContainerDarkMediumContrast,
        onErrorContainer = onErrorContainerDarkMediumContrast,
        background = backgroundDarkMediumContrast,
        onBackground = onBackgroundDarkMediumContrast,
        surface = surfaceDarkMediumContrast,
        onSurface = onSurfaceDarkMediumContrast,
        surfaceVariant = surfaceVariantDarkMediumContrast,
        onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
        outline = outlineDarkMediumContrast,
        outlineVariant = outlineVariantDarkMediumContrast,
        scrim = scrimDarkMediumContrast,
        inverseSurface = inverseSurfaceDarkMediumContrast,
        inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
        inversePrimary = inversePrimaryDarkMediumContrast,
        surfaceDim = surfaceDimDarkMediumContrast,
        surfaceBright = surfaceBrightDarkMediumContrast,
        surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
        surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
        surfaceContainer = surfaceContainerDarkMediumContrast,
        surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
        surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
    )

private val highContrastDarkColorScheme =
    darkColorScheme(
        primary = primaryDarkHighContrast,
        onPrimary = onPrimaryDarkHighContrast,
        primaryContainer = primaryContainerDarkHighContrast,
        onPrimaryContainer = onPrimaryContainerDarkHighContrast,
        secondary = secondaryDarkHighContrast,
        onSecondary = onSecondaryDarkHighContrast,
        secondaryContainer = secondaryContainerDarkHighContrast,
        onSecondaryContainer = onSecondaryContainerDarkHighContrast,
        tertiary = tertiaryDarkHighContrast,
        onTertiary = onTertiaryDarkHighContrast,
        tertiaryContainer = tertiaryContainerDarkHighContrast,
        onTertiaryContainer = onTertiaryContainerDarkHighContrast,
        error = errorDarkHighContrast,
        onError = onErrorDarkHighContrast,
        errorContainer = errorContainerDarkHighContrast,
        onErrorContainer = onErrorContainerDarkHighContrast,
        background = backgroundDarkHighContrast,
        onBackground = onBackgroundDarkHighContrast,
        surface = surfaceDarkHighContrast,
        onSurface = onSurfaceDarkHighContrast,
        surfaceVariant = surfaceVariantDarkHighContrast,
        onSurfaceVariant = onSurfaceVariantDarkHighContrast,
        outline = outlineDarkHighContrast,
        outlineVariant = outlineVariantDarkHighContrast,
        scrim = scrimDarkHighContrast,
        inverseSurface = inverseSurfaceDarkHighContrast,
        inverseOnSurface = inverseOnSurfaceDarkHighContrast,
        inversePrimary = inversePrimaryDarkHighContrast,
        surfaceDim = surfaceDimDarkHighContrast,
        surfaceBright = surfaceBrightDarkHighContrast,
        surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
        surfaceContainerLow = surfaceContainerLowDarkHighContrast,
        surfaceContainer = surfaceContainerDarkHighContrast,
        surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
        surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
    )

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
private fun isContrastAvailable(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun isDynamicColorAvailable(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

/**
 * From compose-samples/Reply/app/src/main/java/com/example/reply/ui/theme/Type.kt
 *
 * @see [compose-samples](https://github.com/android/compose-samples).
 *
 * TODO(b/336693596): UIModeManager is not yet supported in preview.
 */
@SuppressLint("NewApi") // Suppresses false positive.
@Composable
private fun getContrastAwareColorScheme(isDark: Boolean, isEnabled: Boolean): ColorScheme {
    val defaultColorScheme = if (isDark) darkScheme else lightScheme
    if (!isEnabled || LocalInspectionMode.current || isContrastAvailable().not()) {
        return defaultColorScheme
    }
    val uiModeManager =
        LocalContext.current.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    val contrastLevel = uiModeManager.contrast

    return when (contrastLevel) {
        in 0.0f..0.33f -> defaultColorScheme
        in 0.34f..0.66f ->
            if (isDark) mediumContrastDarkColorScheme else mediumContrastLightColorScheme
        in 0.67f..1.0f -> if (isDark) highContrastDarkColorScheme else highContrastLightColorScheme
        else -> defaultColorScheme
    }
}

@Composable
fun AppTheme(
    isDarkThemeEnabled: Boolean = isSystemInDarkTheme(),
    isDynamicColorEnabled: Boolean = true,
    isContrastAwarenessEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            isDynamicColorEnabled && isDynamicColorAvailable() -> {
                if (isDarkThemeEnabled) {
                    dynamicDarkColorScheme(LocalContext.current)
                } else {
                    dynamicLightColorScheme(LocalContext.current)
                }
            }
            else ->
                getContrastAwareColorScheme(
                    isDark = isDarkThemeEnabled,
                    isEnabled = isContrastAwarenessEnabled,
                )
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = shapes,
        content = content,
    )
}
