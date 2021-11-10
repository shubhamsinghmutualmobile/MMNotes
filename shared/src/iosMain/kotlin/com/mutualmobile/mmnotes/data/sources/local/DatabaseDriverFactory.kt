package com.mutualmobile.mmnotes.data.sources.local

import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(MMNotesDatabase.Schema, "MMNotes.db")
}
