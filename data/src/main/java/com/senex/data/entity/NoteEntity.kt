package com.senex.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
internal data class NoteEntity(

    @PrimaryKey
    val id: Long,
    val text: String,
    val isDone: Boolean,
)
