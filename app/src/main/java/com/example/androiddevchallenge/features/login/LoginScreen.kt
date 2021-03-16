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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.colorDarkBackground
import com.example.androiddevchallenge.core.theme.colorLoginScreenLoginButtonLight
import com.example.androiddevchallenge.core.theme.colorLoginScreenTextFieldBackgroundDark
import com.example.androiddevchallenge.core.theme.colorLoginScreenTextFieldIndicatorLight
import com.example.androiddevchallenge.core.theme.gray800
import com.example.androiddevchallenge.core.theme.gray900
import com.example.androiddevchallenge.core.theme.shapes
import com.example.androiddevchallenge.core.theme.taupe100
import com.example.androiddevchallenge.core.theme.taupe800
import com.example.androiddevchallenge.core.theme.white
import com.example.androiddevchallenge.core.theme.white800

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
    @Composable
    fun LoginTextInput(
        text: String,
        textChanged: (String) -> Unit,
    ) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = when {
                    isDarkTheme -> white800
                    else -> gray800
                },
                backgroundColor = when {
                    isDarkTheme -> colorLoginScreenTextFieldBackgroundDark
                    else -> white
                },
                focusedIndicatorColor = when {
                    isDarkTheme -> white
                    else -> colorLoginScreenTextFieldIndicatorLight
                },
                unfocusedIndicatorColor = when {
                    isDarkTheme -> white
                    else -> colorLoginScreenTextFieldIndicatorLight
                },
            ),
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            value = username,
            placeholder = {
                Text(text)
            },
            onValueChange = textChanged
        )
    }

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
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
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

            LoginTextInput(text = "Username", textChanged = usernameChanged)

            Spacer(modifier = Modifier.height(8.dp))

            LoginTextInput(text = "Password", textChanged = passwordChanged)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when {
                        isDarkTheme -> white
                        else -> colorLoginScreenLoginButtonLight
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
                        style = TextStyle(color = when {
                            isDarkTheme -> taupe100
                            else -> taupe800
                        }),
                        onClick = {
                            onSignUpClicked()
                        },
                    )
                }
            }
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginScreenLightPreview() {
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

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginScreenDarkPreview() {
    Surface(color = colorDarkBackground) {
        LoginScreen(
            username = "Hello",
            usernameChanged = {},
            password = "World",
            passwordChanged = {},
            onLoginClicked = {},
            onSignUpClicked = {},
            isDarkTheme = true,
        )
    }
}
