package com.senex.notetaker.di

import com.senex.notetaker.edit.EditFragment
import com.senex.notetaker.notes.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface FragmentInjectorModule {

    @ContributesAndroidInjector
    fun contributeNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    fun contributeEditFragment(): EditFragment
}
