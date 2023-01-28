package com.senex.notetaker.util

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BindingDaggerFragment<T : ViewBinding> : Fragment(), HasAndroidInjector {

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private var _binding: T? = null
    private val binding
        get() = _binding!!

    protected open fun T.onViewCreated() = Unit

    final override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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
