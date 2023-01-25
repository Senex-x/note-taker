package com.senex.notetaker.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.senex.notetaker.databinding.FragmentEditBinding
import com.senex.notetaker.util.BindingBottomSheetFragment
import javax.inject.Inject

class EditFragment : BindingBottomSheetFragment<FragmentEditBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditBinding =
        FragmentEditBinding::inflate

    private val viewModel: EditViewModel by viewModels { factory }

    override fun FragmentEditBinding.onViewCreated() {

    }
}
