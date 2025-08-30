package cz.pesta.skoda_test.ui.screens.car_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import vwg.skoda.maulcompose.lib.components.MaulCard
import vwg.skoda.maulcompose.lib.components.MaulText
import vwg.skoda.maulcompose.lib.foundation.MaulTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(
    screenState: CarListScreenState,
    onRefresh: () -> Unit,
    onItemClicked: (Car) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = screenState.isLoading,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize()
    ) {
        CarList(screenState.cars, onItemClicked)
    }
}

@Composable
fun CarList(cars: List<Car>, onItemClicked: (Car) -> Unit = {}) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaulTheme.dimensions.paddingListVertical),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = MaulTheme.dimensions.paddingListVertical,
                horizontal = MaulTheme.dimensions.paddingListHorizontal
            )
    ) {
        items(cars.size) { index ->
            CarItem(cars[index], onItemClicked)
        }
    }
}

@Composable
fun CarItem(car: Car, onItemClicked: (Car) -> Unit) {
    val url = car.renders.firstOrNull { render -> render.viewPoint == ViewPoint.GARAGE_L }?.url
    MaulCard(
        onClick = { onItemClicked(car) },
        modifier = Modifier
            // Should use constants for sizes
            .defaultMinSize(minHeight = 200.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = MaulTheme.dimensions.paddingListVertical,
                horizontal = MaulTheme.dimensions.paddingListHorizontal
            )
        ) {
            AsyncImage(
                model = url,
                contentDescription = car.model,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp),
                // Should use placeholder, error and fallback images
                contentScale = ContentScale.Fit
            )
            MaulText(AnnotatedString(car.name), style = MaulTheme.typography.header3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarListPreview() {
    CarList(previewCars)
}

data class CarListScreenState(
    val isLoading: Boolean = false,
    val cars: List<Car> = emptyList()
)