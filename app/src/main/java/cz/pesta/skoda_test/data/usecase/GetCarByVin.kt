package cz.pesta.skoda_test.data.usecase

import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.repository.CarRepository
import javax.inject.Inject

class GetCarByVin @Inject internal constructor(
    private val carRepository: CarRepository
) {
    suspend operator fun invoke(vin: String): Car? {
        return carRepository.getCarByVin(vin)
    }
}