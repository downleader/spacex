package com.example.spacex.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.example.spacex.R;
import com.example.spacex.SpaceXApp;

public final class UiUtils {

    public static boolean isTablet() {
        return SpaceXApp.getInstance()
                .getComponent().getApplicationContext()
                .getResources().getBoolean(R.bool.isTablet);
    }

    @NonNull
    public static String getString(@StringRes int resourceId, @NonNull Object... args) {
        return SpaceXApp.getInstance()
                .getComponent().getApplicationContext()
                .getResources().getString(resourceId, args);
    }

    private UiUtils() {
    }
}
