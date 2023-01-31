package com.senex.core.model

data class Note(

    val id: Long = 0,
    val text: String,
    val isDone: Boolean = false,
)
