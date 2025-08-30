package cz.pesta.skoda_test.ui.screens.car_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.pesta.skoda_test.ui.navigation.Screens

@Composable
fun CarListScreenContainer(
    navController: NavController
) {
    val viewModel = hiltViewModel<CarListViewModel>()

    CarListScreen(
        screenState = viewModel.screenState,
        onRefresh = viewModel::loadCars,
        onItemClicked = { car ->
            navController.navigate(
                Screens.CarDetailScreen.BASE + listOf(
                    car.vin,
                ).joinToString("/")
            )
        }
    )
}