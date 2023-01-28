package com.senex.notetaker.di

import com.senex.notetaker.ui.edit.EditFragment
import com.senex.notetaker.ui.notes.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface FragmentInjectorModule {

    @ContributesAndroidInjector
    fun contributeNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    fun contributeEditFragment(): EditFragment
}
