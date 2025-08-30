package cz.pesta.skoda_test.data.repository

import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.network.Api
import cz.pesta.skoda_test.data.network.helpers.Result
import cz.pesta.skoda_test.data.network.helpers.callSafely
import cz.pesta.skoda_test.data.persistence.dao.CarDao
import cz.pesta.skoda_test.data.persistence.entity.CarEntity
import cz.pesta.skoda_test.data.persistence.mappers.transform
import cz.pesta.skoda_test.helpers.connectedToNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarDataRepository(
    private val api: Api,
    private val carDao: CarDao
) : CarRepository {
    override suspend fun getCars(): Result<List<Car>> {
        if (connectedToNetwork) {
            val cars = callSafely { api.getCars() }
            if (cars is Result.Success) {
                cacheCars(cars.data)
            }
            return cars
        } else {
            val cachedCars = getCachedCars()
            return if (cachedCars.isNotEmpty()) {
                Result.Success(cachedCars)
            } else {
                // Should handle errors properly
                Result.NetworkError
            }
        }
    }

    override suspend fun cacheCars(cars: List<Car>) {
        // Ensure Room DB write off the main thread
        withContext(Dispatchers.IO) {
            carDao.insert(cars.map { transform(it) })
        }
    }

    override suspend fun getCachedCars(): List<Car> {
        // Ensure Room DB read off the main thread
        return withContext(Dispatchers.IO) {
            carDao.getAll().map { transform(it) }
        }
    }

    override suspend fun getCarByVin(vin: String): Car? {
        // Ensure Room DB read off the main thread
        val carEntity: CarEntity? = withContext(Dispatchers.IO) { carDao.getByVin(vin) }
        return carEntity?.let { transform(it) }
    }
}