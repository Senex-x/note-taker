package com.senex.core.repository

import com.senex.core.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository : BaseRepository<Note, Note> {

    fun getAllSorted(): Flow<List<Note>>
}
