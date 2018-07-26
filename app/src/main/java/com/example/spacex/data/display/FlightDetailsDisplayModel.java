package com.example.spacex.data.display;

public class FlightDetailsDisplayModel {

    private final String missionName;
    private final String launchDate;
    private final String launchSite;
    private final boolean launched;
    private final String details;

    private FlightDetailsDisplayModel(Builder builder) {
        missionName = builder.missionName;
        launchDate = builder.launchDate;
        launchSite = builder.launchSite;
        launched = builder.launched;
        details = builder.details;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getLaunchSite() {
        return launchSite;
    }

    public boolean isLaunched() {
        return launched;
    }

    public String getDetails() {
        return details;
    }

    public static final class Builder {

        private String missionName;
        private String launchDate;
        private String launchSite;
        private boolean launched;
        private String details;

        public Builder setMissionName(String missionName) {
            this.missionName = missionName;
            return this;
        }

        public Builder setLaunchDate(String launchDate) {
            this.launchDate = launchDate;
            return this;
        }

        public Builder setLaunchSite(String launchSite) {
            this.launchSite = launchSite;
            return this;
        }

        public Builder setLaunched(boolean launched) {
            this.launched = launched;
            return this;
        }

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        public FlightDetailsDisplayModel build() {
            return new FlightDetailsDisplayModel(this);
        }
    }
}
