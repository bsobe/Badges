package com.github.arturogutierrez.providers

import android.content.Context
import android.content.Intent

private const val INTENT_ACTION: String = "android.intent.action.BADGE_COUNT_UPDATE"
private const val INTENT_EXTRA_BADGE_COUNT: String = "badge_count"
private const val INTENT_EXTRA_PACKAGE_NAME: String = "badge_count_package_name"
private const val INTENT_EXTRA_ACTIVITY_NAME: String = "badge_count_class_name"

class DefaultBadgeProvider(
    private val context: Context,
    homePackage: String
) : BadgeProvider(context, homePackage) {

    override fun setBadgeCount(badgeCount: Int) {
        try {
            val intent = Intent(INTENT_ACTION).apply {
                putExtra(INTENT_EXTRA_BADGE_COUNT, badgeCount)
                putExtra(INTENT_EXTRA_PACKAGE_NAME, packageName)
                putExtra(INTENT_EXTRA_ACTIVITY_NAME, launchIntentClassName)
            }

            BadgeUtil.sendDefaultIntentExplicitly(context, intent)
        } catch (e: Exception) {
            // Some Samsung devices are throwing SecurityException or RuntimeException when
            // trying to set the badge saying the app needs permission which are already added,
            // this try/catch protect us from these "crappy phones" :)
            throw UnsupportedOperationException()
        }
    }
}
