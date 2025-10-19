package com.nabila.memorizequran.data.remote

import com.nabila.memorizequran.data.remote.model.DetailSurahResponse
import com.nabila.memorizequran.data.remote.model.SurahResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/v2/surat")
    suspend fun getAllSurah(): SurahResponse

    @GET("api/v2/surat/{nomor}")
    suspend fun getDetailSurah(@Path("nomor") nomor: Int): DetailSurahResponse
}