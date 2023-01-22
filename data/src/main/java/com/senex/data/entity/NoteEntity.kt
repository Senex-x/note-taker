package com.senex.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senex.core.entity.Note
import com.senex.data.mapper.transform
import com.senex.data.repository.TransformableEntity

@Entity(tableName = "notes")
data class NoteEntity(

    @PrimaryKey
    val id: Long,
    val text: String,
) : TransformableEntity<Note> {

    override fun toModel(): Note = transform()
}
