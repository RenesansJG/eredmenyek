package hu.renesans.eredmenyek.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hu.renesans.eredmenyek.model.database.Team;
import hu.renesans.eredmenyek.model.database.Tournament;

public class MemoryRepository implements Repository {
    public static List<Tournament> tournaments;
    public static List<Team> teams;

    @Override
    public void open(Context context) {
        Tournament tournament1 = new Tournament(1L, "Premier League", "images/tournaments/premier_league.png");
        Tournament tournament2 = new Tournament(8L, "La Liga", "images/tournaments/la_liga.png");

        Team team1 = new Team(5L, "Manchester United", "images/teams/manchester_united.png");
        Team team2 = new Team(18L, "Barcelona", "images/teams/barcelona.png");

        tournaments = new ArrayList<>();
        tournaments.add(tournament1);
        tournaments.add(tournament2);

        teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
    }

    @Override
    public void close() {
    }

    @Override
    public List<Tournament> getFavoriteTournaments() {
        return tournaments;
    }

    @Override
    public List<Team> getFavoriteTeams() {
        return teams;
    }

    @Override
    public void saveFavorite(Tournament tournament) {
        tournaments.add(tournament);
    }

    @Override
    public void saveFavorite(Team team) {
        teams.add(team);
    }

    @Override
    public void removeFavorite(Tournament tournament) {
        tournaments.remove(tournament);
    }

    @Override
    public void removeFavorite(Team team) {
        teams.remove(team);
    }

    @Override
    public boolean isInDb(Tournament tournament) {
        return tournaments.contains(tournament);
    }

    @Override
    public boolean isInDb(Team team) {
        return teams.contains(team);
    }
}
