package com.senex.notetaker.initializer

import android.content.Context
import androidx.startup.Initializer
import com.senex.notetaker.di.ContextComponent
import com.senex.notetaker.di.ContextComponentHolder

class MainInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        ContextComponentHolder.set { ContextComponent { context } }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
