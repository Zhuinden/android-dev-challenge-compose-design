package com.example.androiddevchallenge.features.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.*
import coil.request.ImageRequest
import coil.size.PixelSize
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.black800
import com.example.androiddevchallenge.core.theme.colorDarkBackground
import com.example.androiddevchallenge.core.theme.colorHomeScreenCardBackgroundDark
import com.example.androiddevchallenge.core.theme.colorHomeScreenCardBackgroundLight
import com.example.androiddevchallenge.core.theme.colorHomeScreenFabCircleBackgroundLight
import com.example.androiddevchallenge.core.theme.colorHomeScreenTextFieldBackgroundDark
import com.example.androiddevchallenge.core.theme.colorHomeScreenTextFieldIndicatorLight
import com.example.androiddevchallenge.core.theme.gray800
import com.example.androiddevchallenge.core.theme.taupe100
import com.example.androiddevchallenge.core.theme.taupe800
import com.example.androiddevchallenge.core.theme.white
import com.example.androiddevchallenge.core.theme.white800
import com.example.androiddevchallenge.data.ImageData
import dev.chrisbanes.accompanist.coil.CoilImage
import okhttp3.HttpUrl

@Composable
fun HomeScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    imagesGridFirstRow: List<ImageData>,
    imagesGridSecondRow: List<ImageData>,
    imagesCardRows: List<Pair<String, List<ImageData>>>,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    @Composable
    fun CategoryText(text: String) {
        Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.BottomStart) {
            Text(
                text = text,
                fontStyle = MaterialTheme.typography.h2.fontStyle,
                color = when {
                    isDarkTheme -> taupe100
                    else -> taupe800
                }
            )
        }
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

    @Composable
    fun CircleImageWithText(imageData: ImageData) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .size(LocalDensity.current.run { PixelSize(88.dp.toPx().toInt(), 88.dp.toPx().toInt()) })
                    .data(HttpUrl.parse(imageData.imageUrl))
                    .build(),
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                contentDescription = imageData.title,
                contentScale = ContentScale.FillBounds,
                loading = {
                    CircularProgressIndicator()
                },
            )

            Box(
                modifier = Modifier
                    .defaultMinSize(88.dp)
                    .height(24.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Text(text = imageData.title, fontStyle = MaterialTheme.typography.h3.fontStyle, color = when {
                    isDarkTheme -> white800
                    else -> black800
                })
            }
        }
    }

    @Composable
    fun GridCardRow(imageDatas: List<ImageData>) {
        Row {
            imageDatas.fastForEach { imageData ->
                GridCard(imageData)
            }
        }
    }

    @Composable
    fun CircleImageRow(imageDatas: List<ImageData>) {
        val rowScrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 112.dp)
                .offset(x = -8.dp) // counteract item padding
                .horizontalScroll(rowScrollState),
        ) {
            imageDatas.fastForEach { imageData ->
                CircleImageWithText(imageData)
            }
        }
    }

    @Composable
    fun BottomNavItem(
        @DrawableRes indicatorImageLight: Int,
        @DrawableRes indicatorImageDark: Int,
        text: String,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(modifier = Modifier.size(18.dp), painter = painterResource(when {
                isDarkTheme -> indicatorImageDark
                else -> indicatorImageLight
            }), contentDescription = null)

            Text(
                text = text,
                color = when {
                    isDarkTheme -> taupe100
                    else -> taupe800
                },
                fontStyle = MaterialTheme.typography.caption.fontStyle
            )
        }
    }

    Surface(color = when {
        isDarkTheme -> colorDarkBackground
        else -> taupe100
    }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
        ) {
            val columnScrollState = rememberScrollState()
            Column(
                modifier = Modifier.verticalScroll(columnScrollState)
            ) {
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

                        CategoryText(text = "FAVORITE COLLECTIONS")

                        Spacer(modifier = Modifier.height(8.dp))

                        val scrollState = rememberScrollState()
                        Column(modifier = Modifier.horizontalScroll(scrollState)) {
                            GridCardRow(imageDatas = imagesGridFirstRow)

                            Spacer(modifier = Modifier.height(8.dp))

                            GridCardRow(imageDatas = imagesGridSecondRow)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        imagesCardRows.fastForEach { (title, imageDatas) ->
                            CategoryText(text = title)

                            CircleImageRow(imageDatas)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Layout(
                modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
                measurePolicy = MeasurePolicy { measurables, constraints ->
                    val placeables = measurables.fastMap { it.measure(constraints) }
                    val maxWidth = placeables.fastMaxBy { it.width }?.width ?: 0
                    val maxHeight = placeables.fastMaxBy { it.height }?.height ?: 0

                    var correctedOffset = -maxWidth/4

                    layout(maxWidth, maxHeight) {
                        placeables.fastForEach { placeable ->
                            placeable.place(x = correctedOffset, y = 0)

                            correctedOffset += maxWidth / 2
                        }
                    }
                },
                content = {
                    BottomNavItem(
                        indicatorImageLight = R.drawable.ic_baseline_local_florist_selected_light_24,
                        indicatorImageDark = R.drawable.ic_baseline_local_florist_selected_dark_24,
                        text = "HOME"
                    )

                    BottomNavItem(
                        indicatorImageLight = R.drawable.ic_baseline_face_unselected_light_24,
                        indicatorImageDark = R.drawable.ic_baseline_face_unselected_dark_24,
                        text = "PROFILE"
                    )
                }
            )

            Column {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
                    modifier = Modifier.size(56.dp),
                    backgroundColor = when {
                        isDarkTheme -> white
                        else -> colorHomeScreenFabCircleBackgroundLight
                    },
                    shape = CircleShape,
                    onClick = {
                    },
                    content = {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = when {
                                isDarkTheme -> R.drawable.ic_baseline_play_arrow_24_dark
                                else -> R.drawable.ic_baseline_play_arrow_24_light
                            }),
                            contentDescription = "Play",
                        )
                    }
                )

                Spacer(modifier = Modifier.height(28.dp))
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
        imagesGridFirstRow = listOf(ImageData.SHORT_MANTRAS, ImageData.STRESS_AND_ANXIETY, ImageData.OVERWHELMED),
        imagesGridSecondRow = listOf(ImageData.NATURE_MEDITATIONS, ImageData.SELF_MASSAGE, ImageData.NIGHTLY_WIND_DOWN),
        imagesCardRows = listOf(
            "ALIGN YOUR BODY" to listOf(ImageData.INVERSIONS, ImageData.QUICK_YOGA, ImageData.STRETCHING, ImageData.TABATA, ImageData.HIIT, ImageData.PRE_NATAL_YOGA),
            "ALIGN YOUR MIND" to listOf(ImageData.MEDITATE, ImageData.WITH_KIDS, ImageData.AROMATHERAPY, ImageData.ON_THE_GO, ImageData.WITH_PETS, ImageData.HIGH_STRESS)
        ),
        isDarkTheme = false,
    )
}

@Preview
@Composable
fun HomeScreenDarkPreview() {
    HomeScreen(
        searchText = "",
        onSearchTextChanged = {},
        imagesGridFirstRow = listOf(ImageData.SHORT_MANTRAS, ImageData.STRESS_AND_ANXIETY, ImageData.OVERWHELMED),
        imagesGridSecondRow = listOf(ImageData.NATURE_MEDITATIONS, ImageData.SELF_MASSAGE, ImageData.NIGHTLY_WIND_DOWN),
        imagesCardRows = listOf(
            "ALIGN YOUR BODY" to listOf(ImageData.INVERSIONS, ImageData.QUICK_YOGA, ImageData.STRETCHING, ImageData.TABATA, ImageData.HIIT, ImageData.PRE_NATAL_YOGA),
            "ALIGN YOUR MIND" to listOf(ImageData.MEDITATE, ImageData.WITH_KIDS, ImageData.AROMATHERAPY, ImageData.ON_THE_GO, ImageData.WITH_PETS, ImageData.HIGH_STRESS)
        ),
        isDarkTheme = true,
    )
}