package com.instance.dreamjournal

import android.app.Application
import com.instance.dreamjournal.ui.home.homeModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class DreamJournalApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DreamJournalApp)
            modules(homeModule)
        }
    }
}