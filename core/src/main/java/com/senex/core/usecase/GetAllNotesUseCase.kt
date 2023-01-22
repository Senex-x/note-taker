package com.senex.core.usecase

import com.senex.core.entity.Note
import com.senex.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    operator fun invoke(): Flow<List<Note>> = noteRepository.getAll()
}
