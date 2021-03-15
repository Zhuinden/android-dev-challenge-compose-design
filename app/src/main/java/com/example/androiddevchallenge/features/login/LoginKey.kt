package com.example.androiddevchallenge.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.navigation.ComposeKey
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import androidx.compose.runtime.getValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginKey(private val noArgPlaceholder: String = ""): ComposeKey()  {
    override fun bindServices(serviceBinder: ServiceBinder) {
        super.bindServices(serviceBinder)

        with(serviceBinder) {
            add(LoginModel(backstack))
        }
    }

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val model = rememberService<LoginModel>()

        val username by model.username.subscribeAsState("")
        val password by model.password.subscribeAsState("")

        LoginScreen(
            username = username,
            usernameChanged = model::onUsernameChanged,
            password = password,
            passwordChanged = model::onPasswordChanged,
            onLoginClicked = model::onLoginClicked,
            onSignUpClicked = model::onSignupClicked,
        )
    }
}