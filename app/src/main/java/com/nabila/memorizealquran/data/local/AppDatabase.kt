package com.nabila.memorizealquran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nabila.memorizealquran.data.local.entity.SurahEntity

@Database(entities = [SurahEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun surahDao(): SurahDao
}