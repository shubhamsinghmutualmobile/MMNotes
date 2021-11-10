package com.mutualmobile.mmnotes.data.sources.models

data class NoteEntity(
    val id: Int? = null,
    val title: String,
    val body: String,
    val dateCreated: Long
)
