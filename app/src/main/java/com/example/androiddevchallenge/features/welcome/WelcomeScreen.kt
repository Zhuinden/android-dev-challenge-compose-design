package com.example.androiddevchallenge.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.colorWelcomeScreenSignUpButtonLight
import com.example.androiddevchallenge.core.theme.gray900
import com.example.androiddevchallenge.core.theme.rust300
import com.example.androiddevchallenge.core.theme.rust600
import com.example.androiddevchallenge.core.theme.shapes
import com.example.androiddevchallenge.core.theme.taupe800
import com.example.androiddevchallenge.core.theme.white

@Composable
fun WelcomeScreen(
    onSignUpClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    Image(
        painter = painterResource(id = when {
            isDarkTheme -> R.drawable.dark_welcome
            else -> R.drawable.light_welcome
        }),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .zIndex(-9999f)
            .fillMaxSize(),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = buildAnnotatedString {
                    append("MYSOOTHE")
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 2, length)
                    addStyle(SpanStyle(fontFeatureSettings = "'kern' 1.25"), 0, length)
                },
                style = MaterialTheme.typography.h1,
                color = when {
                    isDarkTheme -> white
                    else -> taupe800
                },
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when {
                        isDarkTheme -> white
                        else -> colorWelcomeScreenSignUpButtonLight
                    }
                ),
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth(),
                onClick = onSignUpClicked
            ) {
                Text(
                    text = "SIGN UP",
                    color = when {
                        isDarkTheme -> gray900
                        else -> white
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when {
                        isDarkTheme -> rust300
                        else -> rust600
                    }
                ),
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth(),
                onClick = onLoginClicked
            ) {
                Text(
                    text = "LOG IN",
                    color = when {
                        isDarkTheme -> gray900
                        else -> white
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    WelcomeScreen(
        onSignUpClicked = {},
        onLoginClicked = {}
    )
}