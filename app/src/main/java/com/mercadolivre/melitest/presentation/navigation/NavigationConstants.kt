package com.mercadolivre.melitest.presentation.navigation

const val PRODUCT_ARGUMENT_VALUE = "product"

class NavigationConstants {

    object SCREENS {
        object ROUTE {
            const val HOME = "home_screen"
            const val PRODUCT_RESULT = "product_result_screen/$PRODUCT_ARGUMENT_VALUE"
            const val PRODUCT_DETAIL = "product_detail_screen"
        }
    }
}
