package com.example.forageapp.data

import androidx.room.*
import com.example.forageapp.model.Forageable
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foregeable: Forageable)

    @Update
    suspend fun update(foregeable: Forageable)

    @Delete
    suspend fun delete(foregeable: Forageable)

    @Query("select * from forageable where id=:id")
    fun getForageable(id: Long) : Flow<Forageable>

    @Query("select * from forageable order by id")
    fun getForageables() : Flow<List<Forageable>>

}