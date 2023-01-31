package com.senex.notetaker.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.core.model.Note
import com.senex.core.usecase.GetNoteUseCase
import com.senex.core.usecase.SaveNoteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class EditViewModel @AssistedInject constructor(
    @Assisted noteId: Long,
    getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    val note: StateFlow<Note> = getNoteUseCase(noteId)
        .map { requireNotNull(it) { "Note item by id: $noteId not found" } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Note(text = "")
        )

    fun saveNote(note: Note) {
        viewModelScope.launch {
            saveNoteUseCase(note)
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(noteId: Long): EditViewModel
    }
}

