package com.example.spacex.viewmodel;

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

import javax.inject.Inject;

public class FlightDetailsViewModel extends BaseViewModel {

    public final ObservableBoolean isContentVisible = new ObservableBoolean();
    public final ObservableField<FlightDetailsDisplayModel> details = new ObservableField<>();

    private Flight flight;
    private Listener listener;

    private boolean photoLoaded;

    @Inject
    FlightDetailsViewModel() {
    }

    public void init(@NonNull Flight flight, @NonNull Listener listener) {
        this.flight = flight;
        this.listener = listener;
        setupUi();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!photoLoaded) {
            loadPhoto();
            photoLoaded = true;
        }
    }

    private void setupUi() {
        details.set(mapFlight(flight));
        isContentVisible.set(true);
    }

    private void loadPhoto() {
        if (flight == null) {
            return;
        }
        Links links = flight.links();
        if (links == null) {
            return;
        }
        String photoUrl = links.missionPatchSmall();
        if (!TextUtils.isEmpty(photoUrl)) {
            listener.loadPhoto(photoUrl);
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

    public interface Listener {
        void loadPhoto(@NonNull String photoUrl);
    }
}
