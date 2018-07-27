package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Flight implements Parcelable {

    public static Builder builder() {
        return new AutoValue_Flight.Builder();
    }

    public static TypeAdapter<Flight> typeAdapter(Gson gson) {
        return new AutoValue_Flight.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setFlightNumber(Integer flightNumber);
        public abstract Builder setMissionName(String missionName);
        public abstract Builder setLaunchDateUnix(Long launchDateUnix);
        public abstract Builder setRocket(Rocket rocket);
        public abstract Builder setLaunchSite(LaunchSite launchSite);
        public abstract Builder setLaunchSuccess(Boolean launchSuccess);
        public abstract Builder setDetails(String details);
        public abstract Flight build();
    }

    @Nullable
    @SerializedName("flight_number")
    public abstract Integer flightNumber();

    @Nullable
    @SerializedName("mission_name")
    public abstract String missionName();

    @Nullable
    @SerializedName("launch_date_unix")
    public abstract Long launchDateUnix();

    @Nullable
    @SerializedName("rocket")
    public abstract Rocket rocket();

    @Nullable
    @SerializedName("launch_site")
    public abstract LaunchSite launchSite();

    @Nullable
    @SerializedName("launch_success")
    public abstract Boolean launchSuccess();

    @Nullable
    @SerializedName("details")
    public abstract String details();
}
