package com.mercadolivre.melitest.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.mercadolivre.melitest.R
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.components.CommonProgressSpinner
import com.mercadolivre.melitest.presentation.components.ProductListItem
import com.mercadolivre.melitest.presentation.viewmodel.ProductResultViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun ProductResultScreen(
    viewModel: ProductResultViewModel = koinViewModel(),
    product: String,
    onItemClick: (product: Product) -> Unit,
    onBackButtonClick: () -> Unit,

) {
    val window = (LocalContext.current as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val isLoading = viewModel.state.value.isLoading
    MaterialTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Yellow),
                    modifier = Modifier,
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(onClick = { onBackButtonClick() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                    R.string.product_result_screen_button_back_label,
                                ),
                            )
                        }
                    },
                    actions = {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(55.dp),
                            shape = RoundedCornerShape(24.dp),
                            placeholder = {
                                Text(text = product, textAlign = TextAlign.Center)
                            },
                            value = "",
                            onValueChange = {},
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                            )
                        }
                    },
                )
            },
        ) {
            Spacer(modifier = Modifier.height(132.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                val state = viewModel.state.value
                LaunchedEffect(Unit) {
                    viewModel.searchProductByParameters(product)
                }
                if (state.product.isNotEmpty()) {
                    ProductListScreen(
                        products = state.product,
                        onItemClick = onItemClick,
                    )
                } else {
                    ProductNotFoundError(
                        onBackButtonClick = onBackButtonClick,
                    )
                }
            }
        }
    }
    if (isLoading) {
        CommonProgressSpinner()
    }
}

@Composable
fun ProductListScreen(products: List<Product>, onItemClick: (product: Product) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 100.dp, start = 16.dp),
    ) {
        products.forEachIndexed { index, product ->
            ProductListItem(
                product = product,
                key = index,
                onItemClick = {
                    onItemClick(product)
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ProductNotFoundError(onBackButtonClick: () -> Unit) {
    val productNotFoundText = stringResource(R.string.product_result_screen_product_not_found_text)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = productNotFoundText)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Button(
            onClick = {
                onBackButtonClick()
            },
            modifier = Modifier.width(200.dp).height(50.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = stringResource(R.string.product_result_screen_button_back_label))
        }
    }
}
