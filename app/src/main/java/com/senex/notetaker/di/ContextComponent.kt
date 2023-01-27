package com.senex.notetaker.di

import android.content.Context
import com.senex.data.di.DaggerComponent
import com.senex.data.di.LazyComponentHolder

fun interface ContextComponent : DaggerComponent {

    fun context(): Context
}

object ContextComponentHolder : LazyComponentHolder<ContextComponent>()



