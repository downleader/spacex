package com.example.spacex.data.display;

public class FlightItemDisplayModel {

    private final String missionName;
    private final String launchDate;

    private FlightItemDisplayModel(Builder builder) {
        missionName = builder.missionName;
        launchDate = builder.launchDate;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public static final class Builder {

        private String missionName;
        private String launchDate;

        public Builder setMissionName(String missionName) {
            this.missionName = missionName;
            return this;
        }

        public Builder setLaunchDate(String launchDate) {
            this.launchDate = launchDate;
            return this;
        }

        public FlightItemDisplayModel build() {
            return new FlightItemDisplayModel(this);
        }
    }
}
