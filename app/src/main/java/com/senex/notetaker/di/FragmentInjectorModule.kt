package com.senex.notetaker.di

import com.senex.notetaker.notes.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentInjectorModule {

    @ContributesAndroidInjector
    fun contributeNotesFragment(): NotesFragment
}
