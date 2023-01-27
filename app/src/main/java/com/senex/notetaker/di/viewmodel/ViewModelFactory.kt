package com.senex.notetaker.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

internal typealias ViewModelsProvidersMap =
        Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

internal class ViewModelFactory @Inject constructor(
    private val providers: ViewModelsProvidersMap,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        providers.getValue(modelClass).get() as T
}
