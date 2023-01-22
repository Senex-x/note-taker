package com.senex.data.repository

import com.senex.core.repository.BaseRepository
import com.senex.data.database.BaseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal abstract class BaseRepositoryImpl<
        IN_ENTITY,
        OUT_ENTITY : TransformableEntity<OUT_MODEL>,
        IN_MODEL,
        OUT_MODEL,
        DAO : BaseDao<IN_ENTITY, OUT_ENTITY>,
        >(
    private val dao: DAO,
) : ModelMapper<IN_MODEL, IN_ENTITY>, BaseRepository<IN_MODEL, OUT_MODEL> {

    override fun get(id: Long): Flow<OUT_MODEL?> {
        return dao.get(id).map { entity: OUT_ENTITY? ->
            entity?.toModel()
        }
    }

    override suspend fun insert(item: IN_MODEL) {
        dao.insert(item.toEntity())
    }

    override fun getAll(): Flow<List<OUT_MODEL>> =
        dao.getAll().map { list -> list.map { it.toModel() } }

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

