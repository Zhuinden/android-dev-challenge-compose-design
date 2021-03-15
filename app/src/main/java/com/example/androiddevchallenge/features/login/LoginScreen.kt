package com.example.androiddevchallenge.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.colorLoginButtonLight
import com.example.androiddevchallenge.core.theme.gray900
import com.example.androiddevchallenge.core.theme.shapes
import com.example.androiddevchallenge.core.theme.taupe100
import com.example.androiddevchallenge.core.theme.taupe800
import com.example.androiddevchallenge.core.theme.white

@Composable
fun LoginScreen(
    username: String,
    usernameChanged: (String) -> Unit,
    password: String,
    passwordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    Image(
        painter = painterResource(id = when {
            isDarkTheme -> R.drawable.dark_login
            else -> R.drawable.light_login
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "LOG IN",
                    style = MaterialTheme.typography.h1,
                    color = when {
                        isDarkTheme -> taupe100
                        else -> taupe800
                    },
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )

            TextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = username,
                placeholder = {
                    Text("Username")
                },
                onValueChange = usernameChanged
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                value = password,
                placeholder = {
                    Text("Password")
                },
                onValueChange = passwordChanged
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(shape = shapes.medium, color = when {
                        isDarkTheme -> white
                        else -> colorLoginButtonLight
                    }),
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

            Box(
                modifier = Modifier.height(32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Don't have an account? ",
                        color = when {
                            isDarkTheme -> taupe100
                            else -> taupe800
                        }
                    )

                    ClickableText(
                        text = buildAnnotatedString {
                            append("Sign up")
                            addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 0, length)
                        },
                        onClick = {
                            onSignUpClicked()
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(
        username = "Hello",
        usernameChanged = {},
        password = "World",
        passwordChanged = {},
        onLoginClicked = {},
        onSignUpClicked = {},
        isDarkTheme = false,
    )
}