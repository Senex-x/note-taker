package com.senex.notetaker.ui.edit

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.senex.notetaker.util.ComposeDaggerFragment
import com.senex.notetaker.util.toast
import kotlinx.coroutines.android.awaitFrame
import javax.inject.Inject

internal class EditFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EditViewModel by viewModels { factory }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {

        var shouldOpenDialog by remember { mutableStateOf(true) }

        if (shouldOpenDialog) {
            Dialog(
                properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = { shouldOpenDialog = false }
            ) {
                (LocalView.current.parent as DialogWindowProvider).window
                    .setGravity(Gravity.BOTTOM)

                val keyboard = LocalSoftwareKeyboardController.current
                val focusRequester = remember { FocusRequester() }

                var text by remember { mutableStateOf("") }

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
                            value = text,
                            onValueChange = { text = it },
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
                            onClick = { toast("Save button clicked!") },
                            modifier = Modifier
                                .align(Alignment.End)
                                .weight(1f, false)
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
}