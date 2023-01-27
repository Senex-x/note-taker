package com.senex.data.di

import android.content.Context
import com.senex.core.di.DaggerComponent
import com.senex.core.di.LazyComponentHolder

fun interface ContextComponent : DaggerComponent {

    fun context(): Context
}

object ContextComponentHolder : LazyComponentHolder<ContextComponent>()
