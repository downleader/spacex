package com.example.spacex.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.spacex.R;
import com.example.spacex.data.display.FlightDetailsDisplayModel;
import com.example.spacex.data.response.Flight;
import com.example.spacex.data.response.LaunchSite;
import com.example.spacex.utils.BusinessUtils;
import com.example.spacex.utils.UiUtils;

import javax.inject.Inject;

public class FlightDetailsViewModel extends BaseViewModel {

    public final ObservableField<FlightDetailsDisplayModel> details = new ObservableField<>();

    @Inject
    FlightDetailsViewModel() {
    }

    public void init(@NonNull Flight flight) {
        details.set(mapFlight(flight));
    }

    @NonNull
    private FlightDetailsDisplayModel mapFlight(@NonNull Flight flight) {
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
