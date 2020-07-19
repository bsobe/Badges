package com.github.arturogutierrez.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.arturogutierrez.Badges
import com.github.arturogutierrez.BadgesNotSupportedException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.buttonUpdate).setOnClickListener { updateBadgeCount() }
    }

    private fun updateBadgeCount() {
        val badgeCount =
            findViewById<TextView>(R.id.editTextBadgeCount).text.toString().toIntOrNull()
        badgeCount?.let {
            try {
                Badges.setBadgeCount(this, badgeCount)
            } catch (badgesNotSupportedException: BadgesNotSupportedException) {
                Log.d(MainActivity::class.java.simpleName, badgesNotSupportedException.message!!)
            }
        }
    }

}