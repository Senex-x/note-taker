package com.senex.core.di

import android.content.Context

fun interface ContextComponent : DaggerComponent {

    fun context(): Context
}

object ContextComponentHolder : LazyComponentHolder<ContextComponent>()
