package cz.pesta.skoda_test.data.network

import cz.pesta.skoda_test.data.model.Car
import retrofit2.http.GET

interface Api {
    @GET("garage_api.json")
    suspend fun getCars(): List<Car>
}
