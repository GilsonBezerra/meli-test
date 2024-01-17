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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.utils.formatCurrency

@Composable
fun ProductListItem(
    product: Product,
    key: Int,
    onItemClick: (product: Product) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(product)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CommonProductImage(product.thumbnail)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
        ) {
            Text(text = product.title, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "por ${product.seller.nickname}", fontSize = 10.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${product.price?.formatCurrency()}")
            if (product.installments.quantity <= 10) {
                Row() {
                    Text("em", fontSize = 12.sp)
                    Text(
                        text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()} sem juros",
                        fontSize = 14.sp,
                        color = Color.Green,
                    )
                }
            } else {
                Row() {
                    Text("em", fontSize = 12.sp)
                    Text(
                        text = " ${product.installments.quantity}x ${product.installments.amount.formatCurrency()}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}


