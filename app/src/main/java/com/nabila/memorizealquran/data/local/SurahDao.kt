package com.nabila.memorizealquran.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nabila.memorizealquran.data.local.entity.AyatEntity

@Dao
interface SurahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListAyat(listAyat: List<AyatEntity>)

    @Query("SELECT * FROM ayat")
    suspend fun getAyatList(): List<AyatEntity>
}