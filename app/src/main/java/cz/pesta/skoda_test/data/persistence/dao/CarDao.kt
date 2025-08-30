package cz.pesta.skoda_test.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import cz.pesta.skoda_test.data.persistence.entity.CarEntity
import cz.pesta.skoda_test.data.persistence.entity.CarEntity.Companion.CAR_TABLE

@Dao
abstract class CarDao : BaseDao<CarEntity> {
    @Query("SELECT * FROM $CAR_TABLE")
    abstract fun getAll(): List<CarEntity>

    @Query("SELECT * FROM $CAR_TABLE WHERE vin = :vin LIMIT 1")
    abstract fun getByVin(vin: String): CarEntity?

    @Query("DELETE FROM $CAR_TABLE")
    abstract fun nukeTable()
}