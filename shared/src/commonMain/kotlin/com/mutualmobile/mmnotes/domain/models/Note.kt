package com.mutualmobile.mmnotes.domain.models

data class Note(
    val id: Int? = null,
    val title: String,
    val body: String,
    val dateCreated: Long
)
