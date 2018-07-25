package com.example.spacex.data.response;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@AutoValue
public abstract class FirstStage implements Parcelable {

    public static TypeAdapter<FirstStage> typeAdapter(Gson gson) {
        return new AutoValue_FirstStage.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("cores")
    public abstract ArrayList<StageCore> cores();
}
