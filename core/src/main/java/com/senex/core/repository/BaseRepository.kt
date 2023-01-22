package com.senex.core.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<in T, out E> {

    fun get(id: Long): Flow<E?>

    fun getAll(): Flow<List<E>>

    suspend fun insert(item: T)

    suspend fun insertAll(items: List<T>)

    suspend fun update(item: T)

    suspend fun delete(item: T)

    suspend fun deleteById(id: Long)

    suspend fun deleteAll()
}
