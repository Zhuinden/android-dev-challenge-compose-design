package com.example.androiddevchallenge.features.home

import com.jakewharton.rxrelay2.BehaviorRelay
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.Bundleable
import com.zhuinden.statebundle.StateBundle
import io.reactivex.Observable

class HomeModel(
    private val backstack: Backstack
): Bundleable {
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