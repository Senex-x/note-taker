package com.senex.notetaker.edit

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.senex.core.usecase.GetAllNotesUseCase
import com.senex.notetaker.databinding.FragmentEditBinding
import com.senex.notetaker.util.BindingDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


internal class EditFragment : BindingDialogFragment<FragmentEditBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditBinding =
        FragmentEditBinding::inflate

    private val viewModel: EditViewModel by viewModels { factory }

    override fun FragmentEditBinding.onViewCreated() {

        dialog!!.window!!.run { // TODO Bruh
            setGravity(Gravity.BOTTOM)
            setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }

        lifecycleScope.launch {

            delay(200) // Don't ask please

            showSoftKeyboard(edit)
        }

        saveButton.setOnClickListener {

        }
    }

    // TODO: Bruh
    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
