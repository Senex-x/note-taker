package com.senex.core.usecase

import com.senex.core.model.Note
import com.senex.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    operator fun invoke(id: Long): Flow<Note?> = noteRepository.get(id)
}
