package cz.pesta.skoda_test.ui.screens.car_detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cz.pesta.skoda_test.ui.screens.car_list.CarDetailScreen

@Composable
fun CarDetailScreenContainer() {
    val viewModel = hiltViewModel<CarDetailViewModel>()
    CarDetailScreen(
        screenState = viewModel.screenState,
    )
}