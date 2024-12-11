package com.example.roomdb_c14220017.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface historyBarang_DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: historyBarang)

    @Query("SELECT * FROM historyBarang")
    fun selectAll(): List<historyBarang>
}