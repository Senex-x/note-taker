package com.senex.notetaker.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.senex.notetaker.databinding.FragmentEditBinding
import com.senex.notetaker.util.BindingFragment
import javax.inject.Inject

class EditFragment : BindingFragment<FragmentEditBinding>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: EditViewModel by viewModels { factory }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditBinding =
        FragmentEditBinding::inflate

    override fun FragmentEditBinding.onViewCreated() {

    }
}
