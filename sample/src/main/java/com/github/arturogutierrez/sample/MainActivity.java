package com.github.arturogutierrez.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    EditText badgeCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        badgeCountText = findViewById(R.id.text_badge_count);
    }

    public void updateButtonPressed(View v) {
        int badgeCount = Integer.parseInt(badgeCountText.getText().toString());
        updateBadge(badgeCount);
    }

    private void updateBadge(int count) {
        try {
            Badges.setBadge(this, count);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            Log.d(TAG, badgesNotSupportedException.getMessage());
        }
    }
}
