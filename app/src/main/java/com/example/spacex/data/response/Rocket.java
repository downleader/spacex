package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Rocket implements Parcelable {

    public static TypeAdapter<Rocket> typeAdapter(Gson gson) {
        return new AutoValue_Rocket.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("rocket_id")
    public abstract String rocketId();

    @Nullable
    @SerializedName("rocket_name")
    public abstract String rocketName();

    @Nullable
    @SerializedName("rocket_type")
    public abstract String rocketType();

    @Nullable
    @SerializedName("first_stage")
    public abstract FirstStage firstStage();

    @Nullable
    @SerializedName("second_stage")
    public abstract SecondStage secondStage();
}
