package com.senex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senex.data.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}

