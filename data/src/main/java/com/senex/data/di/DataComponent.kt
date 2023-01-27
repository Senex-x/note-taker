package com.senex.data.di

import android.content.Context
import com.senex.core.di.ComponentHolder
import com.senex.core.di.DaggerComponent
import com.senex.core.repository.NoteRepository
import dagger.Component

interface DataComponent : DaggerComponent {

    fun noteRepository(): NoteRepository
}

@Component(
    dependencies = [
        DataComponentDependencies::class
    ],
    modules = [
        DaoModule::class,
        RepositoryModule::class,
    ]
)
internal interface DataComponentInternal : DataComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: DataComponentDependencies): DataComponentInternal
    }
}

internal interface DataComponentDependencies {

    val context: Context
        get() = ContextComponentHolder.get().context()

    object Impl : DataComponentDependencies
}

object DataComponentHolder : ComponentHolder<DataComponent>() {

    override fun createComponent(): DataComponent =
        DaggerDataComponentInternal.factory().create(DataComponentDependencies.Impl)
}
