package com.senex.data.repository

import com.senex.core.entity.Note
import com.senex.core.repository.BaseRepository
import com.senex.core.repository.NoteRepository
import com.senex.data.database.NoteDao
import com.senex.data.entity.NoteEntity
import com.senex.data.mapper.transform

internal class NoteBaseRepositoryImpl(dao: NoteDao) :
    BaseRepositoryImpl<NoteEntity, NoteEntity, Note, Note, NoteDao>(dao) {

    override fun Note.toEntity(): NoteEntity = transform()
}

internal class NoteRepositoryImpl(private val dao: NoteDao) :
    NoteRepository,
    BaseRepository<Note, Note> by NoteBaseRepositoryImpl(dao)
