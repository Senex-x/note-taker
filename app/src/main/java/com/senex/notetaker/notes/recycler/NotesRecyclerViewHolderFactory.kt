package com.senex.notetaker.notes.recycler

import android.view.View
import com.senex.notetaker.R
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class NotesRecyclerViewHolderFactory : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? =
        when (viewType) {
            R.layout.item_note -> NoteItemViewHolder(view, clicks)
            else -> null
        }
}
