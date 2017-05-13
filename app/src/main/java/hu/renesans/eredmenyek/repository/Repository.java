package hu.renesans.eredmenyek.repository;

import android.content.Context;

import java.util.List;

import hu.renesans.eredmenyek.model.database.Team;
import hu.renesans.eredmenyek.model.database.Tournament;

public interface Repository {
    void open(Context context);

    void close();

    List<Tournament> getFavoriteTournaments();

    List<Team> getFavoriteTeams();

    void saveFavorite(Tournament tournament);

    void saveFavorite(Team team);

    void removeFavorite(Tournament tournament);

    void removeFavorite(Team team);

    boolean isInDb(Tournament tournament);

    boolean isInDb(Team team);
}
