package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("period")
    protected Period period;

    @SerializedName("minute")
    protected Integer minute;

    @SerializedName("type")
    protected EventType type;

    @SerializedName("team")
    protected TeamType team;

    @SerializedName("player")
    protected String player;

    public Event() {
    }

    public Event(Period period, Integer minute, EventType type, TeamType team, String player) {
        this.period = period;
        this.minute = minute;
        this.type = type;
        this.team = team;
        this.player = player;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public TeamType getTeam() {
        return team;
    }

    public void setTeam(TeamType team) {
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
