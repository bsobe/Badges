package com.github.arturogutierrez.providers

import android.content.Context
import android.os.Build

object BadgeProviderFactory {

    fun getBadgeProvider(context: Context): BadgeProvider {
        return when (val homePackage =BadgeUtil.getHomePackage(context)) {
            SamsungBadgeProvider.HOME_PACKAGE -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                DefaultBadgeProvider(context,homePackage)
            } else {
                SamsungBadgeProvider(context)
            }
            LgBadgeProvider.HOME_PACKAGE -> LgBadgeProvider(context)
            SonyBadgeProvider.HOME_PACKAGE -> SonyBadgeProvider(context)
            HtcBadgeProvider.HOME_PACKAGE -> HtcBadgeProvider(context)
            else -> UnsupportedBadgeProvider(context, homePackage)
        }
    }
}
