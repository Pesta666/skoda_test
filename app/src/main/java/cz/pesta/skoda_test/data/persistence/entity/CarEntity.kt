package cz.pesta.skoda_test.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import cz.pesta.skoda_test.data.model.BodyType
import cz.pesta.skoda_test.data.model.Render
import cz.pesta.skoda_test.data.model.ViewPoint
import cz.pesta.skoda_test.data.persistence.entity.BatteryEntity.Companion.BATTERY_TABLE
import cz.pesta.skoda_test.data.persistence.entity.CarEntity.Companion.CAR_TABLE
import cz.pesta.skoda_test.data.persistence.entity.CarEntity.Companion.VIN
import cz.pesta.skoda_test.data.persistence.entity.EngineEntity.Companion.ENGINE_TABLE
import cz.pesta.skoda_test.data.persistence.entity.RenderEntity.Companion.RENDER_TABLE

@Entity(
    tableName = CAR_TABLE, primaryKeys = [VIN]
)
class CarEntity(
    @ColumnInfo(name = VIN) val vin: String,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = MODEL) val model: String,
    @ColumnInfo(name = MODEL_YEAR) val modelYear: String,
    @TypeConverters(BodyTypeConverter::class) val body: BodyType,
    @ColumnInfo(name = TRIM_LEVEL) val trimLevel: String,
    @Embedded val battery: BatteryEntity?,
    @Embedded val engine: EngineEntity,
    @TypeConverters(RenderListConverter::class) val renders: List<Render>,
) {
    companion object {
        // TABLE
        const val CAR_TABLE = "car_table"

        // COLUMN
        const val VIN = "vin"
        const val NAME = "name"
        const val MODEL = "model"
        const val MODEL_YEAR = "model_year"
        const val TRIM_LEVEL = "trim_level"
    }
}

@Entity(tableName = ENGINE_TABLE)
class EngineEntity(
    @PrimaryKey(autoGenerate = true) val engineId: Int = 0,
    @ColumnInfo(name = TYPE) val type: String,
    @ColumnInfo(name = POWER_IN_KW) val powerInKW: Int,
    @ColumnInfo(name = CAPACITY_IN_LITERS) val capacityInLiters: Double? = null
) {
    companion object {
        // TABLE
        const val ENGINE_TABLE = "engine_table"

        // COLUMN
        const val TYPE = "type"
        const val POWER_IN_KW = "power_in_kw"
        const val CAPACITY_IN_LITERS = "capacity_in_liters"
    }
}

@Entity(tableName = RENDER_TABLE)
class RenderEntity(
    @PrimaryKey(autoGenerate = true) val renderId: Int = 0,
    @ColumnInfo(name = URL) val url: String,
    @ColumnInfo(name = VIEW_POINT) val viewPoint: ViewPoint
) {
    companion object {
        // TABLE
        const val RENDER_TABLE = "render_table"

        // COLUMN
        const val URL = "url"
        const val VIEW_POINT = "view_point"
    }
}

@Entity(tableName = BATTERY_TABLE)
class BatteryEntity(
    @PrimaryKey(autoGenerate = true) val batteryId: Int = 0,
    @ColumnInfo(name = CAPACITY_IN_KWH) val capacityInKWh: Int
) {
    companion object {
        // TABLE
        const val BATTERY_TABLE = "battery_table"

        // COLUMN
        const val CAPACITY_IN_KWH = "capacity_in_kwh"
    }
}