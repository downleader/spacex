package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@AutoValue
public abstract class SecondStage implements Parcelable {

    public static TypeAdapter<SecondStage> typeAdapter(Gson gson) {
        return new AutoValue_SecondStage.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("block")
    public abstract Integer block();

    @Nullable
    @SerializedName("payloads")
    public abstract ArrayList<StagePayload> payloads();
}
