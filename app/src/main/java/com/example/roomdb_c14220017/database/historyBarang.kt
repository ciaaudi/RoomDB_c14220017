package com.example.roomdb_c14220017.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyBarang")
data class historyBarang(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tanggal: String,
    val item: String,
    val jumlah: String
)