package com.senex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senex.data.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    // abstract fun scheduleDao(): NoteDao
}

