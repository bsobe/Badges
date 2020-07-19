package com.github.arturogutierrez.providers

import android.content.Context
import android.content.Intent

private const val INTENT_ACTION: String = "com.sonyericsson.home.action.UPDATE_BADGE"
private const val INTENT_PACKAGE_NAME: String = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME"
private const val INTENT_ACTIVITY_NAME: String = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME"
private const val INTENT_SHOW_MESSAGE: String = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE"
private const val INTENT_BADGE_MESSAGE: String = "com.sonyericsson.home.intent.extra.badge.MESSAGE"

class SonyBadgeProvider(private val context: Context) : BadgeProvider(context, HOME_PACKAGE) {

    override fun setBadgeCount(badgeCount: Int) {
        val intent = Intent().apply {
            action = INTENT_ACTION
            putExtra(INTENT_PACKAGE_NAME, packageName)
            putExtra(INTENT_ACTIVITY_NAME, launchIntentClassName)
            putExtra(INTENT_SHOW_MESSAGE, badgeCount > 0)
            putExtra(INTENT_BADGE_MESSAGE, badgeCount.toString())
        }
        context.sendBroadcast(intent)
    }

    companion object{
        const val HOME_PACKAGE = "com.sonyericsson.home"
    }

}