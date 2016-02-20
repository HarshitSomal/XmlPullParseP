package com.assignment.materialdesign.xmlpullparsep;

/**
 * Created by YOUHAI on 1/25/2016.
 */
public class Station {
    String station_name;
    String station_abbr;
    String latitude;
    String longitude;
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStation_abbr() {
        return station_abbr;
    }

    public void setStation_abbr(String station_abbr) {
        this.station_abbr = station_abbr;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }
}
