package cz.pesta.skoda_test.ui.screens.car_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pesta.skoda_test.data.usecase.GetCarByVin
import cz.pesta.skoda_test.ui.screens.car_list.CarDetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCarByVin: GetCarByVin,
) : ViewModel() {

    val vin = requireNotNull(savedStateHandle.get<String>("vin"))

    val car = viewModelScope.launch {
        getCarByVin(vin)
    }

    var screenState: CarDetailScreenState by mutableStateOf(
        CarDetailScreenState()
    )

    init {
        viewModelScope.launch {
            val result = getCarByVin(vin)
            screenState = screenState.copy(car = result)
        }
    }
}