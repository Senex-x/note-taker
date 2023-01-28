package com.senex.notetaker.notes.recycler

import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

internal data class NoteListItem(
    val id: Long,
    val text: String,
    val isDone: Boolean = false,
) : ViewTyped
