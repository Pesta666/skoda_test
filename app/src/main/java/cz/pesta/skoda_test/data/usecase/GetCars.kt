package cz.pesta.skoda_test.data.usecase

import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.network.helpers.Result
import cz.pesta.skoda_test.data.repository.CarRepository
import javax.inject.Inject

class GetCars @Inject internal constructor(
    private val carRepository: CarRepository
) {
    suspend operator fun invoke(): Result<List<Car>> {
        return carRepository.getCars()
    }
}