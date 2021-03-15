package com.example.androiddevchallenge.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.colorDarkBackground
import com.example.androiddevchallenge.core.theme.taupe100
import com.example.androiddevchallenge.core.theme.taupe800

@Composable
fun HomeScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    Surface(color = when {
        isDarkTheme -> colorDarkBackground
        else -> taupe100
    }) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                Column {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = searchText,
                        leadingIcon = {
                            Image(modifier = Modifier.size(18.dp), painter = painterResource(id = when {
                                isDarkTheme -> R.drawable.ic_baseline_search_white_24
                                else -> R.drawable.ic_baseline_search_black_24
                            }), contentDescription = null)
                        },
                        placeholder = {
                            Text("Search")
                        },
                        onValueChange = onSearchTextChanged)

                    Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.BottomStart) {
                        Text(
                            text = "FAVORITE COLLECTIONS",
                            fontStyle = MaterialTheme.typography.h2.fontStyle,
                            color = when {
                                isDarkTheme -> taupe100
                                else -> taupe800
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        searchText = "",
        onSearchTextChanged = {},
        isDarkTheme = false,
    )
}

@Preview
@Composable
fun HomeScreenDarkPreview() {
    HomeScreen(
        searchText = "",
        onSearchTextChanged = {},
        isDarkTheme = true,
    )
}