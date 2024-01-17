package com.mercadolivre.melitest.presentation.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

private val FORMAT_WITH_SYMBOL = DecimalFormatMonetary.getInstance()

fun Number.formatValueToMonetary() = FORMAT_WITH_SYMBOL.format(this)

internal object DecimalFormatMonetary : DecimalFormat() {
    private fun newInstance(func: DecimalFormat.() -> Unit): DecimalFormat {
        val instance = NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
        return instance.apply {
            func()
            applyPattern("R$ #,##0.00")
        }
    }

    fun getInstance() = newInstance {
        val symbol = "R$"
        negativePrefix = "-$symbol "
        negativeSuffix = ""
        positivePrefix = symbol
        maximumFractionDigits = 2
        with(decimalFormatSymbols) {
            currencySymbol = symbol
            groupingSeparator = '.'
            monetaryDecimalSeparator = ','
        }
    }
}

fun Double.formatCurrency(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    currencyFormat.maximumFractionDigits = 2

    val decimalFormat = currencyFormat as DecimalFormat
    decimalFormat.applyPattern("R$ #,##0.00")

    return decimalFormat.format(this / 100.0)
}
