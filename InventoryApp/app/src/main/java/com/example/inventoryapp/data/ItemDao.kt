package com.example.inventoryapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    /*
    * The argument OnConflict tells the Room what to do in case of a conflict.
    * The OnConflictStrategy.IGNORE strategy ignores a new item if it's primary key
    * is already in the database
    * */
    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item:Item)

    @Query("select * from item where id=:id")
    fun getItem(id:Int): Flow<Item>

    @Query("select * from item order by id")
    fun getAllItems():Flow<List<Item>>
}