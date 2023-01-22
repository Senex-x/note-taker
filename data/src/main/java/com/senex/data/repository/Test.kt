package com.senex.data.repository

import com.senex.core.repository.BaseRepository
import com.senex.data.database.BaseDao
import kotlinx.coroutines.flow.Flow

internal abstract class BaseRepositoryTestImpl<IN_ENTITY, OUT_ENTITY, DAO : BaseDao<IN_ENTITY, OUT_ENTITY>, IN_MODEL, OUT_MODEL>(
    private val dao: DAO,
) : BaseRepository<IN_MODEL, OUT_MODEL> {

    override fun get(id: Long): Flow<OUT_MODEL?> {

    }

    override fun getAll(): Flow<List<OUT_MODEL>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(item: IN_MODEL) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg items: IN_MODEL) {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: IN_MODEL) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(item: IN_MODEL) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}
