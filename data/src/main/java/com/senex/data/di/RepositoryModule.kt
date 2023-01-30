package com.senex.data.di

import com.senex.core.repository.NoteRepository
import com.senex.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}
