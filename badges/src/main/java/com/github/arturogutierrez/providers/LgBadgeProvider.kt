package com.github.arturogutierrez.providers

import android.content.Context
import android.content.Intent

class LgBadgeProvider(private val context: Context) : BadgeProvider(context, HOME_PACKAGE) {

    override fun setBadgeCount(badgeCount: Int) {
        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
        intent.putExtra("badge_count_package_name", packageName)
        intent.putExtra("badge_count_class_name", launchIntentClassName)
        intent.putExtra("badge_count", badgeCount)

        context.sendBroadcast(intent);
    }

    companion object {
        const val HOME_PACKAGE = "com.lge.launcher2"
    }

}