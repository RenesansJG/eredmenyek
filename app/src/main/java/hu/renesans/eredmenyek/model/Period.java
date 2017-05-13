package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

public enum Period {
    @SerializedName("first_half")
    FIRST_HALF("first_half"),

    @SerializedName("second_half")
    SECOND_HALF("second_half"),

    @SerializedName("first_extra")
    FIRST_EXTRA("first_extra"),

    @SerializedName("second_extra")
    SECOND_EXTRA("second_extra"),

    @SerializedName("penalties")
    PENALTIES("penalties");

    private String value;

    Period(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
