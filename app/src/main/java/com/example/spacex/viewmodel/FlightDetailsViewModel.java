package com.example.spacex.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.spacex.R;
import com.example.spacex.data.display.FlightDetailsDisplayModel;
import com.example.spacex.data.response.Flight;
import com.example.spacex.data.response.LaunchSite;
import com.example.spacex.data.response.Links;
import com.example.spacex.utils.BusinessUtils;
import com.example.spacex.utils.UiUtils;

public class FlightDetailsViewModel extends BaseViewModel {

    public final ObservableBoolean isContentVisible = new ObservableBoolean();
    public final ObservableField<FlightDetailsDisplayModel> details = new ObservableField<>();

    private final MutableLiveData<String> photoUrl = new MutableLiveData<>();

    private boolean initialized;
    private boolean photoLoaded;

    private Flight flight;

    @NonNull
    public LiveData<String> getPhotoUrl() {
        return photoUrl;
    }

    public void init(@NonNull Flight flight) {
        if (!initialized) {
            this.flight = flight;
        }
        setupUi();
        initialized = true;
        photoLoaded = false;
    }

    public void loadData() {
        if (!photoLoaded) {
            loadPhotoUrl();
            photoLoaded = true;
        }
    }

    private void setupUi() {
        details.set(mapFlight(flight));
        isContentVisible.set(true);
    }

    private void loadPhotoUrl() {
        if (flight == null) {
            return;
        }
        Links links = flight.links();
        if (links == null) {
            return;
        }
        String photoUrlValue = links.missionPatchSmall();
        if (!TextUtils.isEmpty(photoUrlValue)) {
            photoUrl.setValue(photoUrlValue);
        }
    }

    @NonNull
    private static FlightDetailsDisplayModel mapFlight(@NonNull Flight flight) {
        String launchDate = BusinessUtils.formatLaunchDate(flight);
        LaunchSite launchSite = flight.launchSite();
        String launchSiteValue = (launchSite != null)
                ? launchSite.siteNameLong() : "";
        return new FlightDetailsDisplayModel.Builder()
                .setMissionName(flight.missionName())
                .setLaunchDate(UiUtils.getString(R.string.launch_date_label, launchDate))
                .setLaunchSite(UiUtils.getString(R.string.launch_site_label, launchSiteValue))
                .setLaunched(BusinessUtils.safeUnbox(flight.launchSuccess()))
                .setDetails(flight.details())
                .build();
    }
}
