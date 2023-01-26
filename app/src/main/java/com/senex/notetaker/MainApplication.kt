package com.senex.notetaker

import com.senex.notetaker.di.AppComponentDependencies
import com.senex.notetaker.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .dependencies(AppComponentDependencies.Impl)
            .build()
}
