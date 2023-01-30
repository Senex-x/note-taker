package com.senex.data.di

import com.senex.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
internal object DaoModule {

    @Singleton
    @Provides
    fun provideMainDatabase(database: MainDatabase) = database.noteDao()
}
