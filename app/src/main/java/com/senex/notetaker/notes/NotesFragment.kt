package com.senex.notetaker.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.notetaker.R
import com.senex.notetaker.databinding.FragmentNotesBinding
import com.senex.notetaker.edit.EditFragment
import com.senex.notetaker.notes.recycler.NoteItem
import com.senex.notetaker.notes.recycler.NotesRecyclerViewHolderFactory
import com.senex.notetaker.util.BindingFragment
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines
import javax.inject.Inject

class NotesFragment : BindingFragment<FragmentNotesBinding>() {

    @Inject
    lateinit var getAllNotesUseCase: GetAllNotesUseCase

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: NotesViewModel by viewModels { factory }

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
                .collect {
                    //Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    getAllNotesUseCase()

                    EditFragment().show(parentFragmentManager, "tag")
                }
        }
    }

    private fun getItems() = listOf<ViewTyped>(
        NoteItem("Hello!"),
        NoteItem("Thats the second item!!"),
    )
}
