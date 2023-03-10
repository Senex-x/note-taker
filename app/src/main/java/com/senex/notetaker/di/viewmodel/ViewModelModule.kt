package com.senex.notetaker.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senex.notetaker.ui.edit.EditViewModel
import com.senex.notetaker.ui.notes.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory,
    ): ViewModelProvider.Factory

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindNotesViewModel(
        viewModel: NotesViewModel,
    ): ViewModel
}
