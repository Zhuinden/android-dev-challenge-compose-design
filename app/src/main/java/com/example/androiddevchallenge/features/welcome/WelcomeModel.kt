package com.example.androiddevchallenge.features.welcome

import com.example.androiddevchallenge.features.login.LoginKey
import com.zhuinden.simplestack.Backstack

class WelcomeModel(
    private val backstack: Backstack
) {
    fun onSignUpClicked() {
        // no design provided
    }

    fun onLoginClicked() {
        backstack.goTo(LoginKey())
    }
}