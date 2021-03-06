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
package com.example.androiddevchallenge.features.home

import com.jakewharton.rxrelay2.BehaviorRelay
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.Bundleable
import com.zhuinden.statebundle.StateBundle
import io.reactivex.Observable

class HomeModel(
    private val backstack: Backstack
) : Bundleable {
    private val currentSearchText: BehaviorRelay<String> = BehaviorRelay.createDefault("")
    val searchText: Observable<String> = currentSearchText

    fun onSearchTextChanged(searchText: String) {
        currentSearchText.accept(searchText)
    }

    override fun toBundle(): StateBundle = StateBundle().apply {
        putString("searchText", currentSearchText.value)
    }

    override fun fromBundle(bundle: StateBundle?) {
        bundle?.run {
            currentSearchText.accept(getString("searchText", ""))
        }
    }
}
