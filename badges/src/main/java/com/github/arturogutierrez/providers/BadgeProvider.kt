package com.github.arturogutierrez.providers

import android.content.Context
import com.github.arturogutierrez.BadgesNotSupportedException

abstract class BadgeProvider(
    context: Context,
    private val homePackage: String
) {

    internal val packageName = context.packageName
    internal val launchIntentClassName =
        context.packageManager.getLaunchIntentForPackage(packageName)?.component?.className

    internal fun getHomePackage(): String = homePackage

    @Throws(BadgesNotSupportedException::class)
    abstract fun setBadgeCount(badgeCount: Int)

    @Throws(BadgesNotSupportedException::class)
    open fun clearBadgeCount() = setBadgeCount(0)
}
