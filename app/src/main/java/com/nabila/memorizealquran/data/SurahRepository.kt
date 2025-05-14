package com.nabila.memorizealquran.data

import com.nabila.memorizealquran.data.local.SurahDao
import com.nabila.memorizealquran.data.remote.data.DetailSurah
import com.nabila.memorizealquran.data.remote.ApiService
import com.nabila.memorizealquran.data.remote.data.Surah
import javax.inject.Inject

class SurahRepository @Inject constructor (
    private val apiService: ApiService,
    private val surahDao: SurahDao
) {

    suspend fun getAllSurah(): List<Surah> {
        val response = apiService.getAllSurah()
        return response.data
    }

//    suspend fun getAllSurah(): List<Surah> {
//        val localData = surahDao.getAllSurah()
//        return if (localData != null) {
//            localData
//        } else {
//            val remoteData = apiService.getAllSurah().data
//            surahDao.insertAll(remoteData)
//            remoteData
//        }
//    }

    suspend fun getDetailSurah(nomor:Int): DetailSurah {
        val response = apiService.getDetailSurah(nomor)
        return response.data
    }
}