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
) : Bundleable {
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
