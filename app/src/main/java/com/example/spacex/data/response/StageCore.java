package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class StageCore implements Parcelable {

    public static TypeAdapter<StageCore> typeAdapter(Gson gson) {
        return new AutoValue_StageCore.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("core_serial")
    public abstract String coreSerial();

    @Nullable
    @SerializedName("flight")
    public abstract Integer flight();

    @Nullable
    @SerializedName("block")
    public abstract Integer block();

    @Nullable
    @SerializedName("reused")
    public abstract Boolean reused();

    @Nullable
    @SerializedName("land_success")
    public abstract Boolean landSuccess();

    @Nullable
    @SerializedName("landing_type")
    public abstract String landingType();

    @Nullable
    @SerializedName("landing_vehicle")
    public abstract String landingVehicle();
}
