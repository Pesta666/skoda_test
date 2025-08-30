package cz.pesta.skoda_test.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.pesta.skoda_test.data.persistence.dao.CarDao
import cz.pesta.skoda_test.data.persistence.entity.BatteryEntity
import cz.pesta.skoda_test.data.persistence.entity.BodyTypeConverter
import cz.pesta.skoda_test.data.persistence.entity.CarEntity
import cz.pesta.skoda_test.data.persistence.entity.EngineEntity
import cz.pesta.skoda_test.data.persistence.entity.RenderEntity
import cz.pesta.skoda_test.data.persistence.entity.RenderListConverter

@Database(
    entities = [
        CarEntity::class,
        EngineEntity::class,
        BatteryEntity::class,
        RenderEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BodyTypeConverter::class,
    RenderListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
}
