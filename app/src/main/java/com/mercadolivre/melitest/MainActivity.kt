package com.mercadolivre.melitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mercadolivre.melitest.presentation.navigation.NavigationScreen
import com.mercadolivre.melitest.presentation.view.HomeScreen
import com.mercadolivre.melitest.presentation.view.ProductResultScreen
import com.mercadolivre.melitest.ui.theme.MeliTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeliTestTheme {
                Surface {
                    MeliAppTest()
                }
            }
        }
    }
}

@Composable
fun MeliAppTest() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreen.Home.route) {
        composable(
            route = NavigationScreen.Home.route,
            arguments = listOf(navArgument("product") { type = NavType.StringArrayType }),
        ) {
            HomeScreen(
                onSearchButton = { product ->
                    navController.navigate("product_result_screen/$product")
                },
            )
        }
        composable(
            route = "product_result_screen/{product}",
        ) { backstackEntry ->
            backstackEntry.arguments?.getString("product")?.let {
                ProductResultScreen(
                    product = it,
                    onItemClick = {}

                )
            }
        }
    }
}
