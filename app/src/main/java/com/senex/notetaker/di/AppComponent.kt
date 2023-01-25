package com.senex.notetaker.di

import com.senex.notetaker.MainApplication
import com.senex.notetaker.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentInjectorModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(instance: MainApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }
}
