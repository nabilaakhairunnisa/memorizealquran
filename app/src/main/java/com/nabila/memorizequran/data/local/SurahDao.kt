package com.nabila.memorizequran.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nabila.memorizequran.data.local.entity.AyatEntity
import com.nabila.memorizequran.data.local.entity.SurahEntity

@Dao
interface SurahDao {
    @Query("SELECT * FROM surah")
    suspend fun getAllSurah(): List<SurahEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSurah(surahList: List<SurahEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveDetailSurah(detailSurah: DetailSurahEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListAyat(listAyat: List<AyatEntity>)

    @Query("SELECT * FROM ayat")
    suspend fun getAyatList(): List<AyatEntity>

    @Query("DELETE FROM ayat")
    suspend fun deleteListAyat()
}