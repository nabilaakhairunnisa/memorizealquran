package com.nabila.memorizequran.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ayat")
data class AyatEntity(
    @PrimaryKey val nomorAyat: Int,
    val teksArab: String,
    val teksIndonesia: String
)
