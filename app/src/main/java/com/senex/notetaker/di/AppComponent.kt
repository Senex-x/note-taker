package com.senex.notetaker.di

import com.senex.core.di.CoreComponent
import com.senex.core.di.DaggerCoreComponent
import com.senex.data.di.DaggerDataComponent
import com.senex.data.di.DataComponent
import com.senex.data.di.DataComponentDependencies
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
interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(instance: MainApplication)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: AppComponentDependencies): Builder

        fun build(): AppComponent
    }
}

interface AppComponentDependencies {

    val dataComponent: DataComponent
        get() = DataComponentHolder.get().component

    val coreComponent: CoreComponent
        get() = DaggerCoreComponent.create()

    object Impl : AppComponentDependencies
}
