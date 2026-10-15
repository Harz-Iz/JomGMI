package com.jomgmi.app.util;

import android.app.Activity;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jomgmi.app.AboutContactActivity;
import com.jomgmi.app.CoursesActivity;
import com.jomgmi.app.EligibilityActivity;
import com.jomgmi.app.MainActivity;
import com.jomgmi.app.R;

public class NavigationHelper {

    public static void setupBottomNavigation(Activity activity, BottomNavigationView bottomNav, int currentItemId) {
        if (bottomNav == null || activity == null) return;

        bottomNav.setSelectedItemId(currentItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == currentItemId) {
                return true; // already on this screen
            }

            Intent intent = null;
            if (itemId == R.id.nav_home) {
                intent = new Intent(activity, MainActivity.class);
            } else if (itemId == R.id.nav_explore) {
                intent = new Intent(activity, CoursesActivity.class);
            } else if (itemId == R.id.nav_eligibility) {
                intent = new Intent(activity, EligibilityActivity.class);
            } else if (itemId == R.id.nav_about) {
                intent = new Intent(activity, AboutContactActivity.class);
            }

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
                if (!(activity instanceof MainActivity)) {
                    activity.finish();
                }
                return true;
            }
            return false;
        });
    }
}
