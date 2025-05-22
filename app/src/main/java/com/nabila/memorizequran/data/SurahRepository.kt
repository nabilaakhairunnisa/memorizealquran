package com.nabila.memorizequran.data

import com.nabila.memorizequran.data.local.SurahDao
import com.nabila.memorizequran.data.local.entity.AyatEntity
import com.nabila.memorizequran.data.local.entity.SurahEntity
import com.nabila.memorizequran.data.remote.model.DetailSurah
import com.nabila.memorizequran.data.remote.ApiService
import com.nabila.memorizequran.data.remote.model.Surah
import javax.inject.Inject

class SurahRepository @Inject constructor (
    private val apiService: ApiService,
    private val surahDao: SurahDao
) {

    private suspend fun getAllSurahFromApi(): List<Surah> {
        val response = apiService.getAllSurah()
        return response.data
    }

    suspend fun getDetailSurah(nomor:Int): DetailSurah {
        val response = apiService.getDetailSurah(nomor)
        return response.data
    }

    private suspend fun saveALlSurahToDb(): Result<Unit> {
        return try {
            val surahFromApi = getAllSurahFromApi()
            val surahEntity = surahFromApi.map { convertModelToEntity(it) }
            surahDao.saveAllSurah(surahEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun convertModelToEntity(surah: Surah): SurahEntity {
        return SurahEntity (
            nomor = surah.nomor,
            nama = surah.nama,
            namaLatin = surah.namaLatin,
            jumlahAyat = surah.jumlahAyat
        )
    }

    suspend fun saveListAyatToDb(listAyat: List<AyatEntity>): Result<Unit> {
        return try {
            surahDao.deleteListAyat()
            surahDao.saveListAyat(listAyat)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllSurahFromDb(): List<SurahEntity> {
        saveALlSurahToDb()
        return surahDao.getAllSurah()
    }

    suspend fun getListAyatFromDb(): List<AyatEntity> {
        return surahDao.getAyatList()
    }
}