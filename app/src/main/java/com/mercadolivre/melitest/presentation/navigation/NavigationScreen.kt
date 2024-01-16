package com.mercadolivre.melitest.presentation.navigation

enum class NavigationScreen(
    val route: String,
) {
    Home(
        route = NavigationConstants.SCREENS.ROUTE.HOME,
    ),
    ProductResult(
        route = NavigationConstants.SCREENS.ROUTE.PRODUCT_RESULT,
    ),
    ProductDetail(
        route = NavigationConstants.SCREENS.ROUTE.PRODUCT_DETAIL,
    ),
}
