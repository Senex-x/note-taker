package com.senex.notetaker.ui.edit

import android.os.Bundle
import android.view.Gravity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senex.notetaker.di.viewmodel.assistedViewModel
import com.senex.notetaker.util.ComposeDaggerFragment
import kotlinx.coroutines.android.awaitFrame
import javax.inject.Inject

internal class EditFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var factory: EditViewModel.Factory

    private val noteId: Long by lazy { requireNotNull(arguments).getLong(NOTE_ID_KEY) }

    private val viewModel: EditViewModel by assistedViewModel { factory.create(noteId) }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {

        var shouldOpenDialog by remember { mutableStateOf(true) }

        if (shouldOpenDialog) {
            Dialog(
                properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = { shouldOpenDialog = false }
            ) {
                (LocalView.current.parent as DialogWindowProvider)
                    .window
                    .setGravity(Gravity.BOTTOM)

                val keyboard = LocalSoftwareKeyboardController.current
                val focusRequester = remember { FocusRequester() }
                val note by viewModel.note.collectAsStateWithLifecycle()

                var noteTextFieldValue by remember { mutableStateOf(TextFieldValue()) }

                LaunchedEffect(note) {
                    noteTextFieldValue = TextFieldValue(
                        text = note.text,
                        selection = TextRange(note.text.length)
                    )
                }

                LaunchedEffect(focusRequester) {
                    awaitFrame()
                    awaitFrame()

                    focusRequester.requestFocus()
                    keyboard?.show()
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        TextField(
                            value = noteTextFieldValue,
                            onValueChange = { noteTextFieldValue = it },
                            label = { Text("Edit note") },
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .weight(1f, false)
                                .animateContentSize()
                        )
                        Button(
                            content = { Text("Save") },
                            onClick = {
                                viewModel.saveNote(note.copy(text = noteTextFieldValue.text))
                                shouldOpenDialog = false
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                                .weight(1f, false),
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewEditFragment() {
        Content()
    }

    companion object {

        private const val NOTE_ID_KEY = "noteId"

        fun createArguments(noteId: Long): Bundle = Bundle()
            .apply { putLong(NOTE_ID_KEY, noteId) }
    }
}
