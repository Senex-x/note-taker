package com.senex.data.di

interface DataComponentHolder {

    val component: DataComponent

    companion object {

        fun get(): DataComponentHolder = DataComponentHolderImpl() // TODO Fix new instance creation on each invocation
    }
}

internal class DataComponentHolderImpl : DataComponentHolder {

    override val component: DataComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerDataComponent.factory().create(DataComponentDependencies.Impl)
    }
}
