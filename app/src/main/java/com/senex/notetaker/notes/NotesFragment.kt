package com.senex.notetaker.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.senex.notetaker.databinding.FragmentNotesBinding
import com.senex.notetaker.util.BindingFragment

class NotesFragment : BindingFragment<FragmentNotesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNotesBinding =
        FragmentNotesBinding::inflate

    override fun FragmentNotesBinding.onViewCreated() {

    }

    companion object {

        fun newInstance() = NotesFragment()
    }
}
