package com.senex.notetaker.notes.recycler

import com.senex.notetaker.R
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import kotlin.random.Random

internal data class NoteItem(
    val text: String,
    override val uid: String = Random.nextLong().toString(),
    override val viewType: Int = R.layout.item_note,
) : ViewTyped
