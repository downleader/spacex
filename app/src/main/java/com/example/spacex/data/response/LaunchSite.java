package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class LaunchSite implements Parcelable {

    public static TypeAdapter<LaunchSite> typeAdapter(Gson gson) {
        return new AutoValue_LaunchSite.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("site_id")
    public abstract String siteId();

    @Nullable
    @SerializedName("site_name")
    public abstract String siteName();

    @Nullable
    @SerializedName("site_name_long")
    public abstract String siteNameLong();
}
