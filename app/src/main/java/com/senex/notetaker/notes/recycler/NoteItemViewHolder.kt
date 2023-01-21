package com.senex.notetaker.notes.recycler

import android.view.View
import com.senex.notetaker.databinding.ItemNoteBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

class NoteItemViewHolder(view: View, clicks: TiRecyclerClickListener) :
    BaseViewHolder<NoteItem>(view, clicks) {

    private val binding = ItemNoteBinding.bind(view)

    override fun bind(item: NoteItem) = with(binding) {
        text.text = item.text
    }
}
