package com.senex.notetaker.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senex.notetaker.R
import com.senex.notetaker.databinding.FragmentNotesBinding
import com.senex.notetaker.notes.recycler.NoteItem
import com.senex.notetaker.notes.recycler.NotesRecyclerViewHolderFactory
import com.senex.notetaker.util.BindingFragment
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

class NotesFragment : BindingFragment<FragmentNotesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotesBinding =
        FragmentNotesBinding::inflate

    override fun FragmentNotesBinding.onViewCreated() {

        val recycler = TiRecyclerCoroutines<ViewTyped>(
            recyclerView = notesRecycler,
            holderFactory = NotesRecyclerViewHolderFactory(),
        ) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
        }
        recycler.setItems(getItems())

        lifecycleScope.launch {
            recycler.clickedItem<NoteItem>(R.layout.item_note)
                .map { "TextUi: ${it.text}" }
                .collect { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun getItems() = listOf<ViewTyped>(
        NoteItem("Hello!"),
        NoteItem("Thats the second item!!"),
    )
}
