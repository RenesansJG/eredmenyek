package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MatchHeader {
    @SerializedName("id")
    protected Integer id;

    @SerializedName("homeTeam")
    protected Team homeTeam;

    @SerializedName("awayTeam")
    protected Team awayTeam;

    @SerializedName("startTime")
    protected Date startTime;

    @SerializedName("started")
    protected Boolean started;

    @SerializedName("period")
    protected Period period;

    @SerializedName("minute")
    protected Integer minute;

    @SerializedName("homeScore")
    protected Integer homeScore;

    @SerializedName("awayScore")
    protected Integer awayScore;

    public MatchHeader() {
    }

    public MatchHeader(Integer id, Team homeTeam, Team awayTeam, Date startTime, Boolean started, Period period, Integer minute, Integer homeScore, Integer awayScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = startTime;
        this.started = started;
        this.period = period;
        this.minute = minute;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Boolean isStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
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

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}
