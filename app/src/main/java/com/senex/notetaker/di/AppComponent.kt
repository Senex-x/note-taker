package com.senex.notetaker.di

import com.senex.core.repository.NoteRepository
import com.senex.data.di.DataComponentHolder
import com.senex.notetaker.MainApplication
import com.senex.notetaker.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        AppComponentDependencies::class
    ],
    modules = [
        AndroidInjectionModule::class,
        FragmentInjectorModule::class,
        ViewModelModule::class,
    ]
)
internal interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(instance: MainApplication)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: AppComponentDependencies): Builder

        fun build(): AppComponent
    }
}

internal interface AppComponentDependencies {

    val noteRepository: NoteRepository
        get() = DataComponentHolder.get().noteRepository()

    object Impl : AppComponentDependencies
}
