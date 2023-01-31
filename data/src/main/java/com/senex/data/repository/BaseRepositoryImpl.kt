package com.senex.data.repository

import com.senex.core.repository.BaseRepository
import com.senex.data.database.BaseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BaseRepositoryImpl<
        IN_ENTITY,
        OUT_ENTITY,
        IN_MODEL,
        OUT_MODEL,
        DAO : BaseDao<IN_ENTITY, OUT_ENTITY>,
        >(
    private val dao: DAO,
    private val toEntity: IN_MODEL.() -> IN_ENTITY,
    private val toModel: OUT_ENTITY.() -> OUT_MODEL,
    private val toTypedArray: List<IN_ENTITY>.() -> Array<IN_ENTITY>,
) : BaseRepository<IN_MODEL, OUT_MODEL> {

    override fun get(id: Long): Flow<OUT_MODEL?> =
        dao.get(id).map { it?.toModel() }

    override fun getAll(): Flow<List<OUT_MODEL>> =
        dao.getAll().map { list -> list.map { it.toModel() } }

    override suspend fun insert(item: IN_MODEL): Long =
        dao.insert(item.toEntity())

    override suspend fun insertAll(items: List<IN_MODEL>) =
        dao.insertAll(*items.map { it.toEntity() }.toTypedArray())

    override suspend fun update(item: IN_MODEL) = dao.update(item.toEntity())

    override suspend fun delete(item: IN_MODEL) = dao.delete(item.toEntity())

    override suspend fun deleteById(id: Long) = dao.deleteById(id)

    override suspend fun deleteAll() = dao.deleteAll()
}

