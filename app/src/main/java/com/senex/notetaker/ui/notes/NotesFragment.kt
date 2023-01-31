package com.senex.notetaker.ui.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.lifecycleScope
import com.senex.notetaker.R
import com.senex.notetaker.ui.edit.EditFragment
import com.senex.notetaker.util.ComposeDaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class NotesFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: NotesViewModel by viewModels { factory }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Scaffold(
            content = { padding -> NotesRecycler(padding) },
            floatingActionButton = { Fab() }
        )
    }

    @Composable
    fun Fab() {
        FloatingActionButton(
            onClick = {
                viewLifecycleOwner.lifecycleScope.launch {
                    openEditFragment()
                }
            }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }
    }

    @Composable
    fun NotesRecycler(padding: PaddingValues) {

        val notes by viewModel.notes.collectAsStateWithLifecycle()

        LazyColumn(contentPadding = padding) {
            items(
                items = notes,
                itemContent = { noteItem ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { openEditFragment(noteItem.id) },
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
                                    viewModel.updateNote(noteItem.toModel().copy(isDone = it))
                                },
                            )
                            Text(text = noteItem.text)
                        }
                    }
                }
            )
        }
    }

    private fun openEditFragment(noteId: Long? = null) {
        parentFragmentManager.commit {
            add<EditFragment>(
                containerViewId = R.id.container,
                args = EditFragment.createArguments(noteId)
            )
        }
    }
}
