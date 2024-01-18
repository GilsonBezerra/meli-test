package com.mercadolivre.melitest.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.sp
import com.mercadolivre.melitest.R

@Composable
internal fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClick: () -> Unit,
    isEnable: Boolean = true,
) {
    MaterialTheme {
        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = {
                    onButtonClick()
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    Color(0xFF3483FA),
                    contentColor = Color.White,
                ),
                modifier = modifier
                    .height(dimensionResource(R.dimen.height_45_dp))
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                enabled = isEnable,
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
internal fun SecundaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClick: () -> Unit,
    isEnable: Boolean = true,
) {
    MaterialTheme {
        Row(
            modifier
                .fillMaxWidth(),
        ) {
            Button(
                onClick = {
                    onButtonClick()
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    Color(0xFFE3EDFB),
                    contentColor = Color(0xFF3483FA),
                ),
                modifier = modifier
                    .height(dimensionResource(R.dimen.height_45_dp))
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                enabled = isEnable,

            ) {
                Text(
                    text = text,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
