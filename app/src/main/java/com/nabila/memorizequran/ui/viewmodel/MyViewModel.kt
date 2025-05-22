package com.nabila.memorizequran.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabila.memorizequran.data.SurahRepository
import com.nabila.memorizequran.data.local.entity.AyatEntity
import com.nabila.memorizequran.data.local.entity.SurahEntity
import com.nabila.memorizequran.data.remote.model.DetailSurah
import com.nabila.memorizequran.ui.widget.QuranWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: SurahRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _surahList = MutableStateFlow<List<SurahEntity>>(emptyList())
    val surahList: StateFlow<List<SurahEntity>> = _surahList

    private val _detailSurah = MutableStateFlow<DetailSurah?>(null)
    val detailSurah: StateFlow<DetailSurah?> = _detailSurah

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    init {
        getSurah()
    }

    private fun getSurah() {
        viewModelScope.launch {
            val listSurah = repository.getAllSurahFromDb()
            _surahList.value = listSurah
        }
    }

    fun getDetailSurah(nomor: Int) {
        viewModelScope.launch {
            val detail = repository.getDetailSurah(nomor)
            _detailSurah.value = detail
        }
    }

    fun saveListAyatToDb(listAyat: List<AyatEntity>) {
        viewModelScope.launch {
            val result = repository.saveListAyatToDb(listAyat)
            if (result.isSuccess) {
                _toastMessage.value = "Ayat tersimpan! Silahkan tambah widget di layar utama Anda."
                QuranWidget.updateAllWidgets(context)
            } else {
                val errorMsg = result.exceptionOrNull()?.message
                _toastMessage.value = "Gagal menyimpan ayat: $errorMsg"
            }
        }
    }

    fun resetToastMessage() {
        _toastMessage.value = null
    }
}