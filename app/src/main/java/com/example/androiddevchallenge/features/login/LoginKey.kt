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
package com.example.androiddevchallenge.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.navigation.ComposeKey
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginKey(private val noArgPlaceholder: String = "") : ComposeKey() {
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
