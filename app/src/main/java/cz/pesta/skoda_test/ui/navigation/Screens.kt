package cz.pesta.skoda_test.ui.navigation

const val MAIN_ROUTE_GRAPH = "main"

sealed class Screens(val route: String) {
    data object CarListScreen : Screens("car_list_screen")
    data object CarDetailScreen : Screens("car_detail_screen/{vin}") {
        const val BASE = "car_detail_screen/"
    }
}
