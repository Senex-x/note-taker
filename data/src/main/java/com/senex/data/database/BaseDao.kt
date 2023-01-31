package com.senex.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

internal interface BaseDao<in T, out E> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg item: T)

    fun get(id: Long): Flow<@JvmSuppressWildcards E?>

    fun getAll(): Flow<List<@JvmSuppressWildcards E>>

    @Update
    suspend fun update(item: T)

    @Delete
    suspend fun delete(item: T)

    suspend fun deleteById(id: Long)

    suspend fun deleteAll()
}
