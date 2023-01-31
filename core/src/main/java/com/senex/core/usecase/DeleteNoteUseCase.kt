package com.senex.core.usecase

import com.senex.core.repository.NoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Long): Unit = noteRepository.deleteById(id)
}
