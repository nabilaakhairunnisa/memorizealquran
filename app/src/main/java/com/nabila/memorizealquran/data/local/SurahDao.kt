package com.nabila.memorizealquran.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nabila.memorizealquran.data.local.entity.SurahEntity

@Dao
interface SurahDao {
    @Query("SELECT * FROM surah")
    suspend fun getAllSurah(): List<SurahEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(surahList: List<SurahEntity>)
}