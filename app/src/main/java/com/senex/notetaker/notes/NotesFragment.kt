package com.senex.notetaker.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senex.notetaker.R
import com.senex.notetaker.databinding.FragmentNotesBinding
import com.senex.notetaker.notes.recycler.NoteItem
import com.senex.notetaker.notes.recycler.NoteViewHolderFactory
import com.senex.notetaker.util.BindingFragment
import ru.tinkoff.mobile.tech.ti_recycler.TiRecyclerBuilder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

class NotesFragment : BindingFragment<FragmentNotesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotesBinding =
        FragmentNotesBinding::inflate

    override fun FragmentNotesBinding.onViewCreated() {

        val recycler = TiRecyclerCoroutines<ViewTyped>(
            recyclerView = recycler,
            NoteViewHolderFactory(),
        ) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        recycler.setItems(getItems())

    }

    private fun getItems() = listOf<ViewTyped>(
        NoteItem("Hello!"),
        NoteItem("Thats the second item!!"),
    )
}
