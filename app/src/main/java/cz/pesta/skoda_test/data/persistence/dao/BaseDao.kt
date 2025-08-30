package cz.pesta.skoda_test.data.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {

    /**
     * Insert a entity into the table.
     * @param entity item to insert
     * @return The row ID of the newly inserted entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    /**
     * Insert a list of entities into the table.
     * @param entities items to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<T>): List<Long>

    /**
     * Delete an entity
     * @return A number of entity deleted. This should always be `1`
     */
    @Delete
    fun delete(entity: T): Int

    /**
     * Delete an entities
     * @return A number of entities deleted.
     */
    @Delete
    fun delete(entities: List<T>): Int

}