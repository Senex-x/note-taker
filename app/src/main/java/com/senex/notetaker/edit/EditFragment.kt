package com.senex.notetaker.edit

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            delay(100)

            showSoftKeyboard(edit)

            delay(100)

            val params = root.layoutParams
            params.height += getKeyboardHeight(root)
            root.layoutParams = params
        }
    }

    // TODO: Bruh
    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    // TODO: Causes visual bugs
    private fun getKeyboardHeight(attachedView: View): Int {
        val insets = ViewCompat.getRootWindowInsets(attachedView)
        return insets?.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0
    }
}
