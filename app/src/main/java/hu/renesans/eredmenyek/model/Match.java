package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Match extends MatchHeader {
    @SerializedName("events")
    private List<Event> events;

    public Match() {
        super();
    }

    public Match(Integer id, Team homeTeam, Team awayTeam, Date startTime, Boolean started, Period period, Integer minute, Integer homeScore, Integer awayScore, List<Event> events) {
        super(id, homeTeam, awayTeam, startTime, started, period, minute, homeScore, awayScore);
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
