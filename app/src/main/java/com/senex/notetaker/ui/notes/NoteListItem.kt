package com.senex.notetaker.ui.notes

import com.senex.core.model.Note
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

internal data class NoteListItem(
    val id: Long,
    val text: String,
    val isDone: Boolean = false,
) : ViewTyped {

    companion object {

        fun from(note: Note) = with(note) {
            NoteListItem(
                id = id,
                text = text,
                isDone = isDone,
            )
        }
    }
}
