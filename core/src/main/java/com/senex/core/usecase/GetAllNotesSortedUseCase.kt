package com.senex.core.usecase

import com.senex.core.model.Note
import com.senex.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllNotesSortedUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    operator fun invoke(): Flow<List<Note>> = noteRepository.getAllSorted()
}
