package com.senex.data.database

import androidx.room.Dao
import androidx.room.Query
import com.senex.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface NoteDao : BaseDao<NoteEntity, NoteEntity> {

    @Query("SELECT * FROM notes WHERE id = :id")
    override fun get(id: Long): Flow<NoteEntity?>

    @Query("SELECT * FROM notes")
    override fun getAll(): Flow<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    override suspend fun deleteById(id: Long)

    @Query("DELETE FROM notes")
    override suspend fun deleteAll()

    @Query("SELECT * FROM notes ORDER BY isDone, id DESC")
    fun getAllSorted(): Flow<List<NoteEntity>>
}
