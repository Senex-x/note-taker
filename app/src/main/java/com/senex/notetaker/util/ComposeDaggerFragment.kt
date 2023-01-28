package com.senex.notetaker.util

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.senex.notetaker.ui.theme.NoteTakerTheme
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class ComposeDaggerFragment : Fragment(), HasAndroidInjector {

    @Composable
    abstract fun Compose()

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )
        setContent { NoteTakerTheme { Compose() } }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
