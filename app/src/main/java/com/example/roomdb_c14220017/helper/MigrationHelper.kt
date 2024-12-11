package com.example.roomdb_c14220017.helper

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationHelper {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
            CREATE TABLE IF NOT EXISTS historyBarang (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                tanggal TEXT NOT NULL,
                item TEXT NOT NULL,
                jumlah TEXT NOT NULL
            )
            """
            )
        }
    }
}