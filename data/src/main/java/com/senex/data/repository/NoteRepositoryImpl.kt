package com.senex.data.repository

import com.senex.core.model.Note
import com.senex.core.repository.BaseRepository
import com.senex.core.repository.NoteRepository
import com.senex.data.database.NoteDao
import com.senex.data.mapper.transform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
) : NoteRepository,
    BaseRepository<Note, Note> by BaseRepositoryImpl(
        dao = dao,
        toEntity = { transform() },
        toModel = { transform() },
        toTypedArray = { toTypedArray() },
    ) {

    override fun getAllSorted(): Flow<List<Note>> =
        dao.getAllSorted().map { list -> list.map { it.transform() } }
}
