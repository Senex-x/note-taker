package com.senex.core.usecase

import com.senex.core.entity.Note
import com.senex.core.repository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note): Unit = noteRepository.insert(note)
}
