package com.senex.data.repository

internal interface ModelMapper<Model, Entity> {

    fun Model.toEntity(): Entity
}
