package com.senex.notetaker.edit

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager.LayoutParams
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.senex.notetaker.util.ComposeDaggerDialogFragment
import com.senex.notetaker.util.ComposeDaggerFragment
import com.senex.notetaker.util.toast
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import javax.inject.Inject


internal class EditFragment : ComposeDaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EditViewModel by viewModels { factory }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override val content = @Composable {

        val focusRequester = remember { FocusRequester() }
        val keyboard = LocalSoftwareKeyboardController.current

        LaunchedEffect(focusRequester) {

            awaitFrame()
            awaitFrame()
            focusRequester.requestFocus()
            keyboard?.show()
        }

        val dialogOpen = remember { mutableStateOf(true) }

        if (dialogOpen.value) {
            Dialog(
                onDismissRequest = { dialogOpen.value = false }
            ) {
                val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
                dialogWindowProvider.window.apply {
                    setGravity(Gravity.BOTTOM)
                    setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                }

                val text = remember { mutableStateOf("Text") }

                Column(modifier = Modifier.padding(8.dp)) {
                    TextField(
                        value = text.value,
                        label = { Text("Edit note") },
                        onValueChange = { text.value = it },
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                    )
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { toast("Save button clicked!") }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        viewLifecycleOwner.lifecycleScope.launch {
//            delay(200)
//        }

//        dialog!!.window!!.run { // TODO Bruh
//            setGravity(Gravity.BOTTOM)
//            setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//            //setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
//        }
    }

    @Preview
    @Composable
    fun PreviewEditFragment() {
        content()
    }

//    override fun FragmentEditBinding.onViewCreated() {
//
//        dialog!!.window!!.run { // TODO Bruh
//            setGravity(Gravity.BOTTOM)
//            setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//        }
//
//        lifecycleScope.launch {
//
//            delay(200) // Don't ask please
//
//            showSoftKeyboard(edit)
//        }
//
//        saveButton.setOnClickListener {
//
//        }
//
//
//    }

    // TODO: Bruh
    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
