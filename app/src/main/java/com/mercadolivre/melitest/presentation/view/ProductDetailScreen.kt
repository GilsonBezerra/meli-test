package com.mercadolivre.melitest.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.mercadolivre.melitest.R
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.EMPTY
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.MOCK_TEN_THOUSEND_SOLD
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.SEPARATOR_VERTICAL_BAR
import com.mercadolivre.melitest.presentation.components.CommonProductImageDetailScreen
import com.mercadolivre.melitest.presentation.components.CommonProgressSpinner
import com.mercadolivre.melitest.presentation.components.MainButton
import com.mercadolivre.melitest.presentation.components.SecundaryButton
import com.mercadolivre.melitest.presentation.utils.formatCurrency
import com.mercadolivre.melitest.presentation.viewmodel.ProductDetailViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = koinViewModel(),
    product: Product,
    onBackButtonClick: () -> Unit,
) {
    val window = (LocalContext.current as Activity).window
    val isLoading = remember { mutableStateOf(false) }
    WindowCompat.setDecorFitsSystemWindows(window, false)
    MaterialTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Yellow),
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
                                .align(Alignment.CenterVertically)
                                .height(dimensionResource(id = R.dimen.height_48_dp)),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.shape_24_dp)),
                            singleLine = true,
                            placeholder = {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically),
                                    text = stringResource(id = R.string.product_detail_screen_search_fiel_placeholder),
                                    fontSize = with(
                                        LocalDensity.current,
                                    ) { dimensionResource(id = R.dimen.size_14_sp).toSp() },
                                )
                            },
                            value = EMPTY,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                ProductDetailContent(product)
            }
        }
    }
    if (isLoading.value) {
        CommonProgressSpinner()
    }
}

@Composable
fun ProductDetailContent(
    product: Product,
) {
    Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.space_top_80_dp)))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_all_24_dp)),
    ) {
        Row() {
            Text(
                text = product.condition,
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
            )
            Text(
                SEPARATOR_VERTICAL_BAR,
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
            )
            Text(
                MOCK_TEN_THOUSEND_SOLD,
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
            )
        }
        Text(
            text = product.title,
            fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_16_sp).toSp() },
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            CommonProductImageDetailScreen(product.thumbnail)
        }
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.space_bottom_32_dp)))
        Text(text = "${product.price?.formatCurrency()}")
        if (product.installments.quantity <= 10) {
            Row() {
                Text(
                    stringResource(R.string.common_preprosition_in),
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                )
                Text(
                    text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()} ${
                        stringResource(
                            id = R.string.product_list_item_interest_free_text,
                        )
                    }",
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_14_sp).toSp() },
                    color = Color.Green,
                )
            }
        } else {
            Row() {
                Text(
                    stringResource(id = R.string.common_preprosition_in),
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                )
                Text(
                    text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()}",
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_14_sp).toSp() },
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.space_top_32_dp)))
        Column(modifier = Modifier) {
            MainButton(
                text = stringResource(R.string.product_detail_screen_button_buy_text),
                onButtonClick = {},
            )
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.space_top_8_dp)))
            SecundaryButton(
                text = stringResource(R.string.product_detail_screen_button_add_to_cart_text),
                onButtonClick = { },
            )
        }
    }
}
