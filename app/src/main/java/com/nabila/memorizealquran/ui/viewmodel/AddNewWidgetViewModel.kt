package com.nabila.memorizealquran.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabila.memorizealquran.data.SurahRepository
import com.nabila.memorizealquran.data.remote.data.DetailSurah
import com.nabila.memorizealquran.data.remote.data.Surah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewWidgetViewModel @Inject constructor(private val repository: SurahRepository): ViewModel() {
    private val _surahList = MutableStateFlow<List<Surah>>(emptyList())
    val surahList: StateFlow<List<Surah>> = _surahList

    private val _detailSurah = MutableStateFlow<DetailSurah?>(null)
    val detailSurah: StateFlow<DetailSurah?> = _detailSurah

    init {
        getSurah()
//        viewModelScope.launch {
//            _surahList.value = repository.getAllSurah()
//        }
    }

    private fun getSurah() {
        viewModelScope.launch {
            val listSurah = repository.getAllSurah()
            _surahList.value = listSurah
        }
    }

    fun getDetailSurah(nomor: Int) {
        viewModelScope.launch {
            val detail = repository.getDetailSurah(nomor)
            _detailSurah.value = detail
        }
    }
}