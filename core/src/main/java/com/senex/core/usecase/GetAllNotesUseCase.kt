package com.senex.core.usecase

import com.senex.core.model.Note
import com.senex.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {

    operator fun invoke(): Flow<List<Note>> = noteRepository.getAll()
}
