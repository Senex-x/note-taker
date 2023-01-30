package com.senex.data.di

import android.content.Context
import androidx.room.Room
import com.senex.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context): MainDatabase =
        Room.databaseBuilder(
            context = appContext,
            klass = MainDatabase::class.java,
            name = "database-main"
        ).build()
}
