package com.senex.data.di

import com.senex.data.database.MainDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
internal object DaoModule {

    @Provides
    fun provideMainDatabase(database: MainDatabase) = database.noteDao()
}
