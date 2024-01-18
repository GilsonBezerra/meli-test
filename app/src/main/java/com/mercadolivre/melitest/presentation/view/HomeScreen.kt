package com.mercadolivre.melitest.presentation.view

import android.app.Activity
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.mercadolivre.melitest.R
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.EMPTY
import com.mercadolivre.melitest.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    onSearchButton: (String) -> Unit,
) {
    val window = (LocalContext.current as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_all_24_dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val context = LocalContext.current
                    val searchTextState = remember { mutableStateOf(TextFieldValue()) }
                    val icon = painterResource(id = R.drawable.baseline_search_24)
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = EMPTY,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.height_150_dp))
                            .width(dimensionResource(id = R.dimen.width_350_dp)),
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onKeyEvent {
                                it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                            },
                        value = searchTextState.value,
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onSearchButton.invoke(
                                    searchTextState.value.text.replace(
                                        " ",
                                        "%20",
                                    ),
                                )
                            },
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.product_detail_screen_search_fiel_placeholder),
                            )
                        },
                        onValueChange = {
                            searchTextState.value = it
                        },
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    onSearchButton.invoke(
                                        searchTextState.value.text.replace(
                                            " ",
                                            "%20",
                                        ),
                                    )
                                },
                            ) {
                                Icon(
                                    painter = icon,
                                    contentDescription = EMPTY,
                                    Modifier
                                        .padding(dimensionResource(R.dimen.padding_all_5_dp))
                                        .width(dimensionResource(R.dimen.width_42_dp))
                                        .height(dimensionResource(R.dimen.height_42_dp)),
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}
