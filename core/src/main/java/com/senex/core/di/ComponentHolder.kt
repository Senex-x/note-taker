package com.senex.core.di

abstract class ComponentHolder<T : DaggerComponent> {

    private var component: T? = null

    fun get(): T = component ?: createComponent().also { component = it }

    abstract fun createComponent(): T
}
