package cz.pesta.skoda_test.ui.screens.car_list

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pesta.skoda_test.data.network.helpers.Result.Success
import cz.pesta.skoda_test.data.usecase.GetCars
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCars: GetCars,
    @ApplicationContext private val appContext: Context
) : ViewModel() {
    var screenState: CarListScreenState by mutableStateOf(
        CarListScreenState()
    )

    init {
        loadCars()
    }

    fun loadCars() {
        screenState = screenState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val result = getCars()
                if (result is Success) {
                    screenState = screenState.copy(cars = result.data, isLoading = false)
                } else {
                    Toast.makeText(appContext, result.toString(), Toast.LENGTH_SHORT).show()
                    screenState = screenState.copy(isLoading = false)
                }
            } catch (e: Exception) {
                Toast.makeText(appContext, e.message ?: "Error loading cars", Toast.LENGTH_SHORT)
                    .show()
                screenState = screenState.copy(isLoading = false)
            }
        }
    }
}