package cz.pesta.skoda_test.data.repository

import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.network.helpers.Result

interface CarRepository {
    suspend fun getCars(): Result<List<Car>>

    suspend fun cacheCars(cars: List<Car>)

    suspend fun getCachedCars(): List<Car>

    suspend fun getCarByVin(vin: String): Car?
}