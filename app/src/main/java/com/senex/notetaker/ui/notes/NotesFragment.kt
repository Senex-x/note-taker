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
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.notetaker.R
import com.senex.notetaker.ui.edit.EditFragment
import com.senex.notetaker.util.ComposeDaggerFragment
import javax.inject.Inject
import kotlin.random.Random

internal class NotesFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var getAllNotesUseCase: GetAllNotesUseCase

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: NotesViewModel by viewModels { factory }

    @Composable
    override fun Compose() {

        val items = remember {
            buildList {
                for (i in 0..8) {
                    getItems().forEach {
                        add(it.copy(id = Random.nextLong()))
                    }
                }
            }.toMutableStateList()
        }

        LazyColumn(contentPadding = PaddingValues(all = 16.dp)) {
            items(
                items = items,
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
                                    items[items.indexOf(noteItem)] = noteItem.copy(isDone = it)
                                },
                            )
                            Text(text = noteItem.text)
                        }
                    }
                }
            )
        }
    }

    private fun getItems() = listOf(
        NoteListItem(1, "Hello!"),
        NoteListItem(
            2,
            "Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! Thats the second item!! "
        ),
    )
}
