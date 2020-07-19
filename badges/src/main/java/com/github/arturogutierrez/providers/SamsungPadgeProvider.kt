package com.github.arturogutierrez.providers

import android.content.ContentValues
import android.content.Context
import android.net.Uri

class SamsungBadgeProvider(private val context: Context) : BadgeProvider(context, HOME_PACKAGE) {

    override fun setBadgeCount(badgeCount: Int) {
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            CONTENT_URI, arrayOf(COLUMN_ID),
            "$COLUMN_PACKAGE=?", arrayOf(packageName), null
        )

        try {
            if (cursor == null || cursor.moveToFirst().not()) {
                val contentValues = ContentValues().apply {
                    put(COLUMN_PACKAGE, packageName)
                    put(COLUMN_CLASS, launchIntentClassName)
                    put(COLUMN_BADGE_COUNT, badgeCount)
                }
                contentResolver.insert(CONTENT_URI, contentValues)
            } else {
                val columnIndex = cursor.getColumnIndex(COLUMN_ID)
                val contentValues = ContentValues().apply {
                    put(COLUMN_BADGE_COUNT, badgeCount)
                }
                contentResolver.update(
                    CONTENT_URI,
                    contentValues,
                    "$COLUMN_ID=?",
                    arrayOf(cursor.getInt(columnIndex).toString())
                )
            }
        } catch (e: Exception) {
            // Some Samsung devices are throwing SecurityException or RuntimeException when
            // trying to set the badge saying the app needs permission which are already added,
            // this try/catch protect us from these "crappy phones" :)

            throw UnsupportedOperationException()
        }
    }

    companion object {
        const val HOME_PACKAGE = "com.sec.android.app.launcher"
    }

    private val CONTENT_URI = Uri.parse("content://com.sec.badge/apps")
    private val COLUMN_ID = "_id"
    private val COLUMN_PACKAGE = "package"
    private val COLUMN_CLASS = "class"
    private val COLUMN_BADGE_COUNT = "badgeCount"
}
