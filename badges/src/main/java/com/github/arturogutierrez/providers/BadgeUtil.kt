package com.github.arturogutierrez.providers

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo

object BadgeUtil {

    internal fun getHomePackage(context: Context): String {
        val intent = Intent(Intent.ACTION_MAIN).apply { addCategory(Intent.CATEGORY_HOME) }
        val resolveInfo =
            context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo?.activityInfo?.packageName ?: context.packageName
    }

    internal fun sendDefaultIntentExplicitly(context: Context, intent: Intent) {
        var oreoIntentSuccess = false

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val oreoIntent = Intent(intent)
            oreoIntent.action = "me.leolin.shortcutbadger.BADGE_COUNT_UPDATE"

            oreoIntentSuccess = try {
                sendIntentExplicitly(context, oreoIntent);
                true
            } catch (e: Exception) {
                false
            }
        }

        if (oreoIntentSuccess) {
            return
        }*/

        // try pre-Oreo default intent
        sendIntentExplicitly(context, intent);
    }

    private fun sendIntentExplicitly(context: Context, intent: Intent) {
        val resolveInfoLit: List<ResolveInfo> = resolveBroadcast(context, intent)

        if (resolveInfoLit.isEmpty()) {
            throw Exception("unable to resolve intent: $intent")
        }

        resolveInfoLit.forEach {
            val actualIntent = Intent(intent)
            actualIntent.setPackage(it.resolvePackageName)
            context.sendBroadcast(actualIntent)
        }
    }

    private fun resolveBroadcast(context: Context, intent: Intent): List<ResolveInfo> {
        val packageManager = context.packageManager
        return packageManager.queryBroadcastReceivers(intent, 0)
    }
}
