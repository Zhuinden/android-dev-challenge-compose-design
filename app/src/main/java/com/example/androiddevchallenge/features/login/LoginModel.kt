package com.example.androiddevchallenge.features.login

import com.example.androiddevchallenge.features.home.HomeKey
import com.jakewharton.rxrelay2.BehaviorRelay
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.Bundleable
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.statebundle.StateBundle
import io.reactivex.Observable

class LoginModel(
    private val backstack: Backstack,
): Bundleable {
    private val currentUsername: BehaviorRelay<String> = BehaviorRelay.createDefault("")
    private val currentPassword: BehaviorRelay<String> = BehaviorRelay.createDefault("")

    val username: Observable<String> = currentUsername
    val password: Observable<String> = currentPassword

    fun onUsernameChanged(username: String) {
        this.currentUsername.accept(username)
    }

    fun onPasswordChanged(password: String) {
        this.currentPassword.accept(password)
    }

    fun onLoginClicked() {
        backstack.setHistory(History.of(HomeKey()), StateChange.REPLACE)
    }

    fun onSignupClicked() {
        // no design provided
    }

    override fun toBundle(): StateBundle = StateBundle().apply {
        putString("username", currentUsername.value)
        putString("password", currentPassword.value)
    }

    override fun fromBundle(bundle: StateBundle?) {
        bundle?.run {
            currentUsername.accept(getString("username", ""))
            currentPassword.accept(getString("password", ""))
        }
    }
}