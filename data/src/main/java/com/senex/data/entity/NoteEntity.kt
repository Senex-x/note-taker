package com.senex.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
internal data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String,
    val isDone: Boolean,
)
