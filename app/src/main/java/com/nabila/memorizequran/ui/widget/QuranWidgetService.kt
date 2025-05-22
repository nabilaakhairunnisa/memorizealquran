package com.nabila.memorizequran.ui.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.nabila.memorizequran.data.SurahRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuranWidgetService: RemoteViewsService() {
    @Inject
    lateinit var repository: SurahRepository

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return QuranWidgetFactory(applicationContext, repository)
    }
}