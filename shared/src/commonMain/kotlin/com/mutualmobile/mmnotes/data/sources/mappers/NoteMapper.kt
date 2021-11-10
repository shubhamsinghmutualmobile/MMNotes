package com.mutualmobile.mmnotes.data.sources.mappers

import com.mutualmobile.mmnotes.data.sources.models.NoteEntity
import com.mutualmobile.mmnotes.domain.models.Note

fun NoteEntity.toNote(): Note = Note(
    id = id,
    title = title,
    body = body,
    dateCreated = dateCreated
)
