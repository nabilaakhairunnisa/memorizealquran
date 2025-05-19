package com.nabila.memorizealquran.ui.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.nabila.memorizealquran.R
import com.nabila.memorizealquran.data.SurahRepository
import com.nabila.memorizealquran.data.local.entity.AyatEntity
import kotlinx.coroutines.runBlocking

class QuranWidgetFactory(
    private val context: Context,
    private val repository: SurahRepository
): RemoteViewsService.RemoteViewsFactory {

    private var ayatList: List<AyatEntity> = emptyList()

    override fun onCreate() {
        // initialize data
        runBlocking {
            ayatList = repository.getListAyat()
        }
    }

    override fun onDataSetChanged() {
        //refresh data
        runBlocking {
            ayatList = repository.getListAyat()
        }
    }

    override fun onDestroy() {
        ayatList = emptyList()
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

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}