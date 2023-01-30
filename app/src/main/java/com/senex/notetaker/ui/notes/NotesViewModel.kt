package com.senex.notetaker.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.core.model.Note
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.core.usecase.SaveNoteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class NotesViewModel @Inject constructor(
    getAllNotesUseCase: GetAllNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    val notes: Flow<List<NoteListItem>> = getAllNotesUseCase()
        .map { list -> list.map { NoteListItem.fromModel(it) } }

    val notesTest: Flow<List<Note>> = getAllNotesUseCase()

    fun saveNote(note: Note) {
        viewModelScope.launch {
            saveNoteUseCase(note)
        }
    }
}


