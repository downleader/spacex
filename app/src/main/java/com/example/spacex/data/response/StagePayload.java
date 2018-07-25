package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class StagePayload implements Parcelable {

    public static TypeAdapter<StagePayload> typeAdapter(Gson gson) {
        return new AutoValue_StagePayload.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("payload_id")
    public abstract String payloadId();

    @Nullable
    @SerializedName("reused")
    public abstract Boolean reused();

    @Nullable
    @SerializedName("payload_type")
    public abstract String payloadType();

    @Nullable
    @SerializedName("payload_mass_kg")
    public abstract String payloadMassKg();

    @Nullable
    @SerializedName("orbit")
    public abstract String orbit();
}
