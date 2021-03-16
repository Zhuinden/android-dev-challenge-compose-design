/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(16.dp),
)


val gray900 = Color(0xFF333333)
val taupe100 = Color(0xFFF0EAE2)
val white = Color(0xFFFFFFFF)
val taupe800 = Color(0xFF655454)
val rust600 = Color(0xFF886363)
val white850 = Color(0xD9FFFFFF)
val gray800 = Color(0xCC333333)
val white150 = Color(0x26FFFFFF)
val rust300 = Color(0xE1AFAF)
val black800 = Color(0xCC000000)
val white800 = Color(0xCCFFFFFF)
val colorDarkBackground = Color(0xFF323232)
val colorLoginScreenLoginButtonLight = Color(0xFF313333)
val colorWelcomeScreenSignUpButtonLight = Color(0xFF323232)
val colorLoginScreenTextFieldBackgroundDark = Color(0xFF585858)
val colorLoginScreenTextFieldIndicatorLight = Color(0xFF636362)
val colorHomeScreenTextFieldBackgroundDark = Color(0xFF585858)
val colorHomeScreenTextFieldIndicatorLight = Color(0xFF636362)
val colorHomeScreenCardBackgroundLight = Color(0xFFFDFCFB)
val colorHomeScreenCardBackgroundDark = Color(0xFF535353)


val typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.kulimpark_light),
        ),
        fontSize = 28.sp,
        letterSpacing = 1.15.sp,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.kulimpark_regular)
        ),
        fontSize = 15.sp,
        letterSpacing = 1.15.sp,
        fontFeatureSettings = "c2sc, smcp",
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(
            Font(resId = R.font.lato_bold)
        ),
        fontSize = 14.sp,
        letterSpacing = 0.sp,
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.lato_regular)
        ),
        fontSize = 14.sp,
        letterSpacing = 0.sp,
    ),
    button = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.lato_bold)
        ),
        fontSize = 14.sp,
        letterSpacing = 1.15.sp,
        fontFeatureSettings = "c2sc, smcp",
    ),
    caption = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.kulimpark_regular)
        ),
        fontSize = 12.sp,
        letterSpacing = 1.15.sp,
        fontFeatureSettings = "c2sc, smcp",
    ),
)


private val LightColorPalette = lightColors(
    primary = gray900,
    background = taupe100,
    onPrimary = white,
    onBackground = taupe800,
    secondary = rust600,
    surface = white850,
    onSecondary = white,
    onSurface = gray800,
)

private val DarkColorPalette = darkColors(
    primary = white,
    background = gray900,
    onPrimary = gray900,
    onBackground = taupe100,
    secondary = rust300,
    surface = white150,
    onSecondary = gray900,
    onSurface = white800,
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
