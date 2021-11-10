package com.mutualmobile.mmnotes.data.sources.local

import android.content.Context
import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(MMNotesDatabase.Schema, context, "MMNotes.db")
}
