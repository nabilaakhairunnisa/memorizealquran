package com.nabila.memorizealquran.data.local.entity

import androidx.room.Entity

@Entity(tableName = "detail_surah")
data class DetailSurahEntity(
    val nomor: Int,
    val jumlahAyat: Int,
    val ayat: List<AyatEntity>
)