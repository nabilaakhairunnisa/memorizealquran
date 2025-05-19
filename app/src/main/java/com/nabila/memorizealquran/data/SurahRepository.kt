package com.nabila.memorizealquran.data

import com.nabila.memorizealquran.data.local.SurahDao
import com.nabila.memorizealquran.data.local.entity.AyatEntity
import com.nabila.memorizealquran.data.remote.model.DetailSurah
import com.nabila.memorizealquran.data.remote.ApiService
import com.nabila.memorizealquran.data.remote.model.Surah
import javax.inject.Inject

class SurahRepository @Inject constructor (
    private val apiService: ApiService,
    private val surahDao: SurahDao
) {

    suspend fun getAllSurah(): List<Surah> {
        val response = apiService.getAllSurah()
        return response.data
    }

    suspend fun getDetailSurah(nomor:Int): DetailSurah {
        val response = apiService.getDetailSurah(nomor)
        return response.data
    }

    suspend fun saveListAyat(listAyat: List<AyatEntity>) {
        surahDao.saveListAyat(listAyat)
    }

    suspend fun getListAyat(): List<AyatEntity> {
        return surahDao.getAyatList()
    }
}