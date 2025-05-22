package com.nabila.memorizequran.ui.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.compose.runtime.collectAsState
import com.nabila.memorizequran.R
import com.nabila.memorizequran.data.SurahRepository
import com.nabila.memorizequran.data.local.entity.AyatEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QuranWidgetFactory(
    private val context: Context,
    private val repository: SurahRepository
): RemoteViewsService.RemoteViewsFactory {

    private var ayatList: List<AyatEntity> = emptyList()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate() {
        // initialize data
        runBlocking {
            ayatList = repository.getListAyatFromDb()
        }
    }

    override fun onDataSetChanged() {
        //refresh data
        runBlocking {
            ayatList = repository.getListAyatFromDb()
        }
    }

    override fun onDestroy() {
        ayatList = emptyList()
        coroutineScope.cancel()
    }

    override fun getCount(): Int = ayatList.size

    override fun getViewAt(position: Int): RemoteViews {
        val ayat = ayatList[position]
        val views = RemoteViews(context.packageName, R.layout.ayat_item)
        views.setTextViewText(R.id.tvArab, ayat.teksArab)
        views.setTextViewText(R.id.tvIndo, ayat.teksIndonesia)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1 //one layout for all item

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}