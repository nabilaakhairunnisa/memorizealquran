package com.nabila.memorizequran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int
)


