package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Links implements Parcelable {

    public static TypeAdapter<Links> typeAdapter(Gson gson) {
        return new AutoValue_Links.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("mission_patch_small")
    public abstract String missionPatchSmall();
}
