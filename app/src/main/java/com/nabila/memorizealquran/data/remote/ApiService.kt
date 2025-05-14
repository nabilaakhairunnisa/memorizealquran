package com.nabila.memorizealquran.data.remote

import com.nabila.memorizealquran.data.remote.data.DetailSurahResponse
import com.nabila.memorizealquran.data.remote.data.SurahResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/v2/surat")
    suspend fun getAllSurah(): SurahResponse

    @GET("api/v2/surat/{nomor}")
    suspend fun getDetailSurah(@Path("nomor") nomor: Int): DetailSurahResponse
}