package cz.pesta.skoda_test.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.pesta.skoda_test.ui.screens.car_detail.CarDetailScreenContainer
import cz.pesta.skoda_test.ui.screens.car_list.CarListScreenContainer
import vwg.skoda.maulcompose.lib.components.MaulToolbar

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val isDetail = currentRoute?.startsWith(Screens.CarDetailScreen.BASE) == true

            if (isDetail) {
                MaulToolbar(
                    // Maybe should use car name here
                    title = "Škoda test",
                    navigation = MaulToolbar.Navigation.Back(
                        enabled = true,
                        onClick = { navController.navigateUp() })
                )
            } else {
                MaulToolbar(title = "Škoda test")
            }
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = Screens.CarListScreen.route,
                route = MAIN_ROUTE_GRAPH
            ) {
                composable(Screens.CarListScreen.route) {
                    CarListScreenContainer(
                        navController = navController,
                    )
                }
                composable(
                    route = Screens.CarDetailScreen.route,
                    arguments = listOf(
                        navArgument("vin") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    CarDetailScreenContainer()
                }
            }
        }
    }
}
