package com.senex.data.repository

internal interface TransformableEntity<Model> {

    fun toModel(): Model
}
