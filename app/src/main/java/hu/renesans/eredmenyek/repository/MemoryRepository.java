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
        tournaments = new ArrayList<>();
        teams = new ArrayList<>();
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
