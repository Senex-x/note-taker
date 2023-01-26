package com.senex.data.di

import com.senex.core.repository.NoteRepository
import com.senex.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoryModule {

    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}
