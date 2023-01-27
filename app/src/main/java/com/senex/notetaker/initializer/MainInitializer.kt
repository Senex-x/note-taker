package com.senex.notetaker.initializer

import android.content.Context
import androidx.startup.Initializer
import com.senex.data.di.ContextComponent
import com.senex.data.di.ContextComponentHolder

internal class MainInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        ContextComponentHolder.set { ContextComponent { context } }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
