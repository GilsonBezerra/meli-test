@file:Suppress("UNREACHABLE_CODE")

package com.mercadolivre.melitest.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.mercadolivre.melitest.R
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.FLOAT_ONE
import com.mercadolivre.melitest.presentation.commons.PresentationConstants.Companion.TEN
import com.mercadolivre.melitest.presentation.utils.formatCurrency

@Composable
fun ProductListItem(
    product: Product,
    key: Int,
    onItemClick: (product: Product) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.height_180_dp))
            .fillMaxWidth()
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(product)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CommonProductImageList(product.thumbnail)
        Column(
            modifier = Modifier
                .weight(FLOAT_ONE)
                .padding(dimensionResource(id = R.dimen.padding_all_16_dp)),
        ) {
            ProductInfo(product)
        }
    }
}

@Composable
private fun ProductInfo(product: Product) {
    val prepOr = stringResource(id = R.string.common_preprosition_or)
    val prepIn = stringResource(id = R.string.common_preprosition_in)
    val prepBy = stringResource(id = R.string.common_preprosition_by)
    val interestFreeText = stringResource(id = R.string.product_list_item_interest_free_text)
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = product.title,
            fontWeight = FontWeight.Normal,
            fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_16_sp).toSp() },
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_top_8_dp)))
        Text(
            text = "$prepBy ${product.seller.nickname}",
            fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_10_sp).toSp() },
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_top_8_dp)))
        if (product.originalPrice != null) {
            Text(
                text = product.originalPrice.formatCurrency(),
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                color = Color.Gray,
            )
        }
        Text(text = "${product.price?.formatCurrency()}")
        if (product.installments.quantity <= TEN) {
            Row() {
                if (product.salePrice != null) {
                    Text(
                        "$prepOr ",
                        fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.padding_end_4_dp)),
                    )
                    Text(
                        text = "${product.salePrice}",
                        fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                    )
                }
                Text(
                    "$prepIn ",
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() },
                )
                Text(
                    text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()} $interestFreeText",
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_14_sp).toSp() },
                    color = Color.Green,
                )
            }
        } else {
            Row() {
                Text("$prepIn ", fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_12_sp).toSp() })
                Text(
                    text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()}",
                    fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.size_14_sp).toSp() },
                )
            }
        }
    }
}
