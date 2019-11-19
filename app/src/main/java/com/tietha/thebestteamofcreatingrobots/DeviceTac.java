package com.tietha.thebestteamofcreatingrobots;

public class DeviceTac {
    String mName;
    String mAddress;

    public DeviceTac() {
    }

    public DeviceTac(String mName, String mAddress) {
        this.mName = mName;
        this.mAddress = mAddress;
    }

    public String getmName() {
        return mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
