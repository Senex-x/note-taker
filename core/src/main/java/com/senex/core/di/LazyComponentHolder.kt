package com.senex.core.di

abstract class LazyComponentHolder<T : DaggerComponent> {

    private var componentProvider: () -> T = {
        throw NullPointerException("componentProvider for ${this::class.simpleName} not found")
    }
    private var component: T? = null

    fun get(): T = component ?: componentProvider().also { component = it }

    fun set(componentProvider: () -> T) {
        this.componentProvider = componentProvider
    }
}
