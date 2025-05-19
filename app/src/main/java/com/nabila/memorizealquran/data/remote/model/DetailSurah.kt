package com.nabila.memorizealquran.data.remote.model

data class DetailSurah(
    val nomor: Int,
    val jumlahAyat: Int,
    val ayat: List<Ayat>
)