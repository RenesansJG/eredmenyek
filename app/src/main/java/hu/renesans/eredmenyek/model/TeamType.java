package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

public enum TeamType {
    @SerializedName("home")
    HOME("home"),

    @SerializedName("away")
    AWAY("away");

    private String value;

    TeamType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
