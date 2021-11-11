package com.mutualmobile.mmnotes.domain.models

import kotlinx.datetime.Clock

data class Note(
    val id: Int? = null,
    val title: String,
    val body: String,
    val dateCreated: Long = Clock.System.now().toEpochMilliseconds()
)
