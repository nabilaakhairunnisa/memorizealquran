package com.nabila.memorizequran.data.remote.model

data class SurahResponse(
    val code: Int,
    val message: String,
    val data: List<Surah>
)