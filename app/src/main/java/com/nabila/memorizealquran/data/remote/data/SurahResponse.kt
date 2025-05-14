package com.nabila.memorizealquran.data.remote.data

data class SurahResponse(
    val code: Int,
    val message: String,
    val data: List<Surah>
)

