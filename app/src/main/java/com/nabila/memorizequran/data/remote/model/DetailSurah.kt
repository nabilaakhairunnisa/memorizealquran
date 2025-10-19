package com.nabila.memorizequran.data.remote.model

data class DetailSurah(
    val nomor: Int,
    val jumlahAyat: Int,
    val ayat: List<Ayat>
)