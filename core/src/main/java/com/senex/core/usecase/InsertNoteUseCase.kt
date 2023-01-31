package com.senex.core.usecase

import com.senex.core.model.Note
import com.senex.core.repository.NoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note): Long = noteRepository.insert(note)
}
