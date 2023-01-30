package com.senex.notetaker.ui.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senex.core.repository.NoteRepository
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.notetaker.R
import com.senex.notetaker.ui.edit.EditFragment
import com.senex.notetaker.util.ComposeDaggerFragment
import javax.inject.Inject

internal class NotesFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var getAllNotesUseCase: GetAllNotesUseCase

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var noteRepository: NoteRepository

    private val viewModel: NotesViewModel by viewModels { factory }

    @Composable
    override fun Content() {

        val notes: List<NoteListItem> by viewModel.notes.collectAsStateWithLifecycle(initialValue = emptyList())

        LazyColumn(contentPadding = PaddingValues(all = 16.dp)) {
            items(
                items = notes,
                itemContent = { noteItem ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                parentFragmentManager.commit {
                                    add<EditFragment>(R.id.container)
                                }
                            },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Checkbox(
                                checked = noteItem.isDone,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .align(Alignment.Top),
                                onCheckedChange = {
                                    viewModel.saveNote(noteItem.toModel().copy(isDone = it))
                                },
                            )
                            Text(text = noteItem.text)
                        }
                    }
                }
            )
        }
    }
}
