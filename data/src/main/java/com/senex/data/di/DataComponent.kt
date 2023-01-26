package com.senex.data.di

import dagger.Component

@Component(
    dependencies = [
        DataComponentDependencies::class
    ],
    modules = [
        RepositoryModule::class,
    ]
)
interface DataComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: DataComponentDependencies): DataComponent
    }
}

interface DataComponentDependencies {

//    val coreComponent: CoreComponent
//        get() = DaggerCoreComponent.create()

    object Impl : DataComponentDependencies
}
