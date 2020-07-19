package com.github.arturogutierrez.providers

import android.content.Context

class UnsupportedBadgeProvider(
    context: Context,
    homePackage: String
) : BadgeProvider(context, homePackage) {

    override fun setBadgeCount(badgeCount: Int) {
        throw UnsupportedOperationException()
    }
}
