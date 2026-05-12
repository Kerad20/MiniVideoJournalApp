package com.example.videojournalapp.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.Database

class DatabaseFactory(
    private val context: Context
) {
    fun create(): Database {
        val driver = AndroidSqliteDriver(
            Database.Schema,
            context,
            "journal.db"
        )

        return Database(driver)
    }
}