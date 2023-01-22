package com.senex.data.mapper

import com.senex.core.entity.Note
import com.senex.data.entity.NoteEntity

fun NoteEntity.transform(): Note = Note(
    id = id,
    text = text,
)

fun Note.transform(): NoteEntity = NoteEntity(
    id = id,
    text = text,
)
