package my.daily.pulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "DailyPulse.Database.db")
        val sqlDriver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")

        try {
            DailyPulseDatabase.Schema.create(sqlDriver)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sqlDriver
    }
}
