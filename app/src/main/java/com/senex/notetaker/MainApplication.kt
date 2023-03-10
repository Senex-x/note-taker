package com.senex.notetaker

import com.senex.notetaker.di.AppComponentHolder
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

internal class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        AppComponentHolder.get()
}
