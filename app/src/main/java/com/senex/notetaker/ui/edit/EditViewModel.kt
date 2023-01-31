package com.senex.notetaker.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.core.model.Note
import com.senex.core.usecase.DeleteNoteUseCase
import com.senex.core.usecase.GetNoteUseCase
import com.senex.core.usecase.InsertNoteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class EditViewModel @AssistedInject constructor(
    @Assisted noteId: Long?,
    getNoteUseCase: GetNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    lateinit var initialTextValue: String

    val note: StateFlow<Note> = if (noteId != null) {
        getNoteUseCase(noteId).map { it ?: Note() }
    } else {
        flowOf(Note())
    }
        .onEach { initialTextValue = it.text }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Note()
        )

    fun saveNote(note: Note) {
        viewModelScope.launch {
            if (note.text != initialTextValue) {
                if (note.text.isNotBlank()) {
                    insertNoteUseCase(note)
                } else {
                    deleteNoteUseCase(note.id)
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(noteId: Long?): EditViewModel
    }
}

