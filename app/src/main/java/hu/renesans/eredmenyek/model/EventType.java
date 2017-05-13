package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

public enum EventType {
    @SerializedName("goal")
    GOAL("goal"),

    @SerializedName("goal_penalty")
    GOAL_PENALTY("goal_penalty"),

    @SerializedName("goal_own")
    GOAL_OWN("goal_own"),

    @SerializedName("missed_penalty")
    MISSED_PENALTY("missed_penalty"),

    @SerializedName("card_yellow")
    CARD_YELLOW("card_yellow"),

    @SerializedName("card_red")
    CARD_RED("card_red"),

    @SerializedName("card_second_yellow_red")
    CARD_SECOND_YELLOW_RED("card_second_yellow_red"),

    @SerializedName("substitution")
    SUBSTITUTION("substitution");

    private String value;

    EventType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
