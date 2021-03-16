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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.navigation.ComposeKey
import com.example.androiddevchallenge.data.ImageData
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeKey(private val noArgsPlaceholder: String = "") : ComposeKey() {
    override fun bindServices(serviceBinder: ServiceBinder) {
        super.bindServices(serviceBinder)

        with(serviceBinder) {
            add(HomeModel(backstack))
        }
    }

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val model = rememberService<HomeModel>()

        val searchText by model.searchText.subscribeAsState(initial = "")

        HomeScreen(
            searchText = searchText,
            onSearchTextChanged = model::onSearchTextChanged,
            imagesGridFirstRow = listOf(ImageData.SHORT_MANTRAS, ImageData.STRESS_AND_ANXIETY, ImageData.OVERWHELMED),
            imagesGridSecondRow = listOf(ImageData.NATURE_MEDITATIONS, ImageData.SELF_MASSAGE, ImageData.NIGHTLY_WIND_DOWN),
            imagesCardRows = listOf(
                "ALIGN YOUR BODY" to listOf(ImageData.INVERSIONS, ImageData.QUICK_YOGA, ImageData.STRETCHING, ImageData.TABATA, ImageData.HIIT, ImageData.PRE_NATAL_YOGA),
                "ALIGN YOUR MIND" to listOf(ImageData.MEDITATE, ImageData.WITH_KIDS, ImageData.AROMATHERAPY, ImageData.ON_THE_GO, ImageData.WITH_PETS, ImageData.HIGH_STRESS)
            ),
            selectedTab = HomeTabs.HOME,
            onSelectedTabChanged = {},
            onPlayButtonClicked = {},
        )
    }
}
