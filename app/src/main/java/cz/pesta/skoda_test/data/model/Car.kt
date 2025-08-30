package cz.pesta.skoda_test.data.model

import com.google.gson.annotations.SerializedName


data class Car(
    val vin: String,
    val name: String,
    val model: String,
    val modelYear: String,
    val body: BodyType,
    val trimLevel: String,
    val battery: Battery? = null,
    val engine: Engine,
    val renders: List<Render>
)

data class Battery(
    val capacityInKWh: Int
)

data class Engine(
    val type: String,
    val powerInKW: Int,
    val capacityInLiters: Double? = null
)

data class Render(
    val url: String,
    val viewPoint: ViewPoint
)

enum class BodyType {
    @SerializedName("Hatchback")
    HATCHBACK,

    @SerializedName("Liftback")
    LIFTBACK
}

enum class ViewPoint {
    @SerializedName("main")
    MAIN,

    @SerializedName("garage_l")
    GARAGE_L
}
