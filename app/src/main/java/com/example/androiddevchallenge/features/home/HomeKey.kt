package com.example.androiddevchallenge.features.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.navigation.ComposeKey
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeKey(private val noArgsPlaceholder: String = ""): ComposeKey() {
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
        )
    }
}