package com.mercadolivre.melitest.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.mercadolivre.melitest.R
import com.mercadolivre.melitest.data.remote.model.ProductResponse
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.components.CommonImage
import com.mercadolivre.melitest.presentation.viewmodel.ProductResultViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun ProductResultScreen(
    viewModel: ProductResultViewModel = koinViewModel(),
    product: String,
    onItemClick: (product: ProductResponse) -> Unit,
) {
    val window = (LocalContext.current as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val searchTextState = remember { mutableStateOf("") }
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
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
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
            val state = viewModel.state.value
            LaunchedEffect(Unit) {
                viewModel.searchProductByParameters(product)
            }
            ProductListScreen(products = state.product)
        }
    }
}

@Composable
fun ProductListScreen(products: List<Product>) {
    LazyColumn {
        items(products.size) {
            ProductCard(product = products[0])
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            CommonImage(data = product.thumbnail)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Seller: ${product.seller}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: ${product.price}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Installment: ${product.installments}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rate: ${product.attributes}")
        }
    }
}

@Composable
fun ProductNotFoundError() {
    val context = LocalContext.current
}
