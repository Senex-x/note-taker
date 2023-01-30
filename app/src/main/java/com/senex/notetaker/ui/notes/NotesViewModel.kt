package com.senex.notetaker.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senex.core.model.Note
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.core.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class NotesViewModel @Inject constructor(
    getAllNotesUseCase: GetAllNotesUseCase,
    saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    private val _notes: MutableStateFlow<List<NoteListItem>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<NoteListItem>> = _notes

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // TODO Inject dispatcher
                getAllNotesUseCase().onEach { notes ->
                    val listItems = notes.map { NoteListItem.from(it) }
                    _notes.emit(listItems)
                }
            }
        }
    }
}


