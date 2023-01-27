package com.senex.notetaker.di

import com.senex.core.di.ComponentHolder
import com.senex.core.di.DaggerComponent
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
internal interface AppComponent : DaggerComponent, AndroidInjector<MainApplication> {

    override fun inject(instance: MainApplication)

    @Component.Factory
    interface Factory {

        fun create(dependencies: AppComponentDependencies): AppComponent
    }
}

internal interface AppComponentDependencies {

    val noteRepository: NoteRepository
        get() = DataComponentHolder.get().noteRepository()

    object Impl : AppComponentDependencies
}

internal object AppComponentHolder : ComponentHolder<AppComponent>() {

    override fun createComponent(): AppComponent =
        DaggerAppComponent.factory().create(AppComponentDependencies.Impl)
}
