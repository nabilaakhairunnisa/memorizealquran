package com.nabila.memorizequran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nabila.memorizequran.data.local.entity.AyatEntity
import com.nabila.memorizequran.data.local.entity.SurahEntity

@Database(
    entities = [
        SurahEntity::class,
        AyatEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun surahDao(): SurahDao
}