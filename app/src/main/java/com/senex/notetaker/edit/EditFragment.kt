package com.senex.notetaker.edit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.senex.notetaker.databinding.FragmentEditBinding
import com.senex.notetaker.util.BindingBottomSheetFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class EditFragment : BindingBottomSheetFragment<FragmentEditBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditBinding =
        FragmentEditBinding::inflate

    private val viewModel: EditViewModel by viewModels { factory }

    override fun FragmentEditBinding.onViewCreated() {

        lifecycleScope.launch {
            delay(300)
            showSoftKeyboard(edit)
        }
    }

    // TODO: Bruh
    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
