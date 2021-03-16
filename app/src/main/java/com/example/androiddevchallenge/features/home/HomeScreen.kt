package com.example.androiddevchallenge.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.size.PixelSize
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.black800
import com.example.androiddevchallenge.core.theme.colorDarkBackground
import com.example.androiddevchallenge.core.theme.colorHomeScreenCardBackgroundDark
import com.example.androiddevchallenge.core.theme.colorHomeScreenCardBackgroundLight
import com.example.androiddevchallenge.core.theme.colorHomeScreenTextFieldBackgroundDark
import com.example.androiddevchallenge.core.theme.colorHomeScreenTextFieldIndicatorLight
import com.example.androiddevchallenge.core.theme.colorLoginScreenTextFieldBackgroundDark
import com.example.androiddevchallenge.core.theme.colorLoginScreenTextFieldIndicatorLight
import com.example.androiddevchallenge.core.theme.gray800
import com.example.androiddevchallenge.core.theme.taupe100
import com.example.androiddevchallenge.core.theme.taupe800
import com.example.androiddevchallenge.core.theme.white
import com.example.androiddevchallenge.core.theme.white800
import com.example.androiddevchallenge.data.ImageData
import dev.chrisbanes.accompanist.coil.CoilImage
import okhttp3.HttpUrl
import java.lang.IllegalArgumentException

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
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = when {
                                isDarkTheme -> white800
                                else -> gray800
                            },
                            backgroundColor = when {
                                isDarkTheme -> colorHomeScreenTextFieldBackgroundDark
                                else -> white
                            },
                            focusedIndicatorColor = when {
                                isDarkTheme -> white
                                else -> colorHomeScreenTextFieldIndicatorLight
                            },
                            unfocusedIndicatorColor = when {
                                isDarkTheme -> white
                                else -> colorHomeScreenTextFieldIndicatorLight
                            },
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        value = searchText,
                        leadingIcon = {
                            Image(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(id = when {
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

                    @Composable
                    fun GridCard(imageData: ImageData) {
                        Row(
                            modifier = Modifier
                                .width(192.dp)
                                .height(56.dp)
                                .padding(4.dp)
                                .background(
                                    color = when {
                                        isDarkTheme -> colorHomeScreenCardBackgroundDark
                                        else -> colorHomeScreenCardBackgroundLight
                                    },
                                    shape = MaterialTheme.shapes.small,
                                )
                        ) {
                            CoilImage(
                                request = ImageRequest.Builder(LocalContext.current)
                                    .size(LocalDensity.current.run { PixelSize(56.dp.toPx().toInt(), 56.dp.toPx().toInt()) })
                                    .data(HttpUrl.parse(imageData.imageUrl))
                                    .build(),
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(MaterialTheme.shapes.small),
                                contentDescription = imageData.title,
                                contentScale = ContentScale.FillBounds,
                                loading = {
                                    CircularProgressIndicator()
                                },
                            )

                            Row(
                                modifier = Modifier
                                    .width(136.dp)
                                    .height(56.dp)
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = imageData.title, fontStyle = MaterialTheme.typography.h3.fontStyle, color = when {
                                    isDarkTheme -> white800
                                    else -> black800
                                })
                            }
                        }
                    }

                    LazyRow(content = {
                        val firstRow = arrayOf(ImageData.SHORT_MANTRAS, ImageData.STRESS_AND_ANXIETY, ImageData.OVERWHELMED)

                        items(count = firstRow.size, key = { index -> firstRow[index].title }) { index ->
                            GridCard(firstRow[index])
                        }
                    })

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(content = {
                        val firstRow = arrayOf(ImageData.NATURE_MEDITATIONS, ImageData.SELF_MASSAGE, ImageData.NIGHTLY_WIND_DOWN)

                        items(count = firstRow.size, key = { index -> firstRow[index].title }) { index ->
                            GridCard(firstRow[index])
                        }
                    })
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