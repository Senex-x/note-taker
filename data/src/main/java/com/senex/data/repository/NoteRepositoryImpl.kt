package com.senex.data.repository

import com.senex.core.model.Note
import com.senex.core.repository.BaseRepository
import com.senex.core.repository.NoteRepository
import com.senex.data.database.NoteDao
import com.senex.data.mapper.transform
import javax.inject.Inject

internal class NoteRepositoryImpl @Inject constructor(
    dao: NoteDao,
) : NoteRepository, BaseRepository<Note, Note> by BaseRepositoryImpl(
    dao = dao,
    toEntity = { transform() },
    toModel = { transform() },
    toTypedArray = { toTypedArray() },
)
