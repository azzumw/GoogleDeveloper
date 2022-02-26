package com.example.forageapp.data

import androidx.room.*
import com.example.forageapp.model.Forageable
import kotlinx.coroutines.flow.Flow

@Dao
interface ForgeableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foregeable: Forageable)

    @Update
    suspend fun update(foregeable: Forageable)

    @Delete
    suspend fun delete(foregeable: Forageable)

    @Query("select * from forgeable_database where id=:id")
    fun getForageable(id: Long) : Flow<Forageable>

    @Query("select * from forgeable_database order by id")
    fun getForageable() : Flow<List<Forageable>>

}