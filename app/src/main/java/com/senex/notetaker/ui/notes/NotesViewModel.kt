package com.senex.notetaker.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.core.model.Note
import com.senex.core.usecase.GetAllNotesSortedUseCase
import com.senex.core.usecase.UpdateNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class NotesViewModel @Inject constructor(
    getAllNotesSortedUseCase: GetAllNotesSortedUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : ViewModel() {

    val notes: StateFlow<List<NoteListItem>> = getAllNotesSortedUseCase()
        .map { list -> list.map { NoteListItem.fromModel(it) } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note)
        }
    }
}


