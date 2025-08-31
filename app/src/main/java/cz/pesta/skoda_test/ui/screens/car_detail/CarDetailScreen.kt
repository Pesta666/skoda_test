package cz.pesta.skoda_test.ui.screens.car_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.model.ViewPoint
import cz.pesta.skoda_test.ui.preview.previewCars
import vwg.skoda.maulcompose.lib.components.MaulLazyList
import vwg.skoda.maulcompose.lib.components.MaulListItemData
import vwg.skoda.maulcompose.lib.components.MaulText
import vwg.skoda.maulcompose.lib.foundation.MaulTheme

@Composable
fun CarDetailScreen(
    screenState: CarDetailScreenState
) {
    if (screenState.car == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (screenState.errorMessage != null) {
                MaulText(
                    text = AnnotatedString(screenState.errorMessage),
                    modifier = Modifier.padding(16.dp),
                    style = MaulTheme.typography.body
                )
            } else {
                CircularProgressIndicator()
            }
        }
    } else {
        CarDetail(screenState.car)
    }
}

@Composable
fun CarDetail(car: Car) {
    Column(
        modifier = Modifier
            .padding(
                vertical = MaulTheme.dimensions.paddingListVertical,
                horizontal = MaulTheme.dimensions.paddingListHorizontal
            )
            .fillMaxSize()
    ) {
        val url = car.renders.firstOrNull { render -> render.viewPoint == ViewPoint.GARAGE_L }?.url
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val engineText = if (car.engine.capacityInLiters != null) {
                "${car.engine.capacityInLiters} ${car.engine.type}"
            } else {
                car.engine.type
            }


            Column {
                if (url != null) {
                    AsyncImage(
                        model = url,
                        contentDescription = "Car image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 200.dp)
                    )
                }
                MaulLazyList(
                    rows = listOf(
                        MaulListItemData(car.name, "Název vozu"),
                        MaulListItemData(car.model, "Model"),
                        MaulListItemData(car.trimLevel, "Stupeň výbavy"),
                        MaulListItemData(car.vin, "VIN"),
                        MaulListItemData(engineText, "Motor"),
                        MaulListItemData("${car.engine.powerInKW} kW", "Maximální výkon")
                    )
                )
            }
        }
    }
}

data class CarDetailScreenState(
    val car: Car? = null,
    val errorMessage: String? = null
)

@Preview(showBackground = true)
@Composable
fun CarDetailListPreview() {
    CarDetailScreen(screenState = CarDetailScreenState(car = previewCars.first()))
}