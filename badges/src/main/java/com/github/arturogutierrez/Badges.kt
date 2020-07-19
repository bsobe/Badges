package com.github.arturogutierrez

import android.content.Context
import com.github.arturogutierrez.providers.BadgeProviderFactory

object Badges {

    @Throws(BadgesNotSupportedException::class)
    fun setBadgeCount(context: Context, badgeCount: Int) {
        val badgeProvider = BadgeProviderFactory.getBadgeProvider(context)
        try {
            badgeProvider.setBadgeCount(badgeCount)
        } catch (exception: UnsupportedOperationException) {
            throw BadgesNotSupportedException(badgeProvider.getHomePackage())
        }
    }

    @Throws(BadgesNotSupportedException::class)
    fun clearBadgeCount(context: Context) {
        val badgeProvider = BadgeProviderFactory.getBadgeProvider(context)
        try {
            badgeProvider.clearBadgeCount()
        } catch (exception: UnsupportedOperationException) {
            throw BadgesNotSupportedException(badgeProvider.getHomePackage())
        }
    }
}
