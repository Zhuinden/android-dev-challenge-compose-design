package com.example.androiddevchallenge.features.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.navigation.ComposeKey
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import kotlinx.parcelize.Parcelize

@Parcelize
data class WelcomeKey(private val noArgsPlaceholder: String = ""): ComposeKey() {
    override fun bindServices(serviceBinder: ServiceBinder) {
        super.bindServices(serviceBinder)

        with(serviceBinder) {
            add(WelcomeModel(backstack))
        }
    }
    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val model = rememberService<WelcomeModel>()

        WelcomeScreen(
            onSignUpClicked = model::onSignUpClicked,
            onLoginClicked = model::onLoginClicked,
        )
    }
}