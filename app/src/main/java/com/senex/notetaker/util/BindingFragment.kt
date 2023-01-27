package com.senex.notetaker.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment

internal abstract class BindingFragment<T : ViewBinding> : DaggerFragment() {

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    private var _binding: T? = null
    private val binding
        get() = _binding!!

    protected open fun T.onViewCreated() = Unit

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = bindingInflater(layoutInflater, container, false).also {
        _binding = it
    }.root

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = binding.onViewCreated()

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


