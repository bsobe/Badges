package com.github.arturogutierrez.providers

import android.content.ComponentName
import android.content.Context
import android.content.Intent

class HtcBadgeProvider(private val context: Context) : BadgeProvider(context, HOME_PACKAGE) {

    override fun setBadgeCount(badgeCount: Int) {
        val intent = Intent("com.htc.launcher.action.UPDATE_SHORTCUT")
        intent.putExtra("packagename", packageName)
        intent.putExtra("count", badgeCount)
        context.sendBroadcast(intent)

        val setNotificationIntent = Intent("com.htc.launcher.action.SET_NOTIFICATION")
        val componentName = ComponentName(packageName, launchIntentClassName!!)
        setNotificationIntent.putExtra(
            "com.htc.launcher.extra.COMPONENT",
            componentName.flattenToShortString()
        )
        setNotificationIntent.putExtra("com.htc.launcher.extra.COUNT", badgeCount)
        context.sendBroadcast(setNotificationIntent)
    }

    companion object {
        const val HOME_PACKAGE = "com.htc.launcher"
    }
}