package com.senex.data.mapper

import com.senex.core.model.Note
import com.senex.data.entity.NoteEntity

internal fun NoteEntity.transform(): Note = Note(
    id = id,
    text = text,
    isDone = isDone,
)

internal fun Note.transform(): NoteEntity = NoteEntity(
    id = id,
    text = text,
    isDone = isDone,
)
