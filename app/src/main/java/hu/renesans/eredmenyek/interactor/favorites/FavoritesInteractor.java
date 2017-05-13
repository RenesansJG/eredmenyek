package hu.renesans.eredmenyek.interactor.favorites;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.favorites.events.GetFavoriteTeamsEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.GetFavoriteTournamentsEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.GetShowFavoriteTeamsOnlyEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.GetShowFavoriteTournamentsOnlyEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.RemoveFavoriteTeamEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.RemoveFavoriteTournamentEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SaveFavoriteTeamEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SaveFavoriteTournamentEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SetShowFavoriteTeamsOnlyEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SetShowFavoriteTournamentsOnlyEvent;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.repository.Repository;
import hu.renesans.eredmenyek.settings.Settings;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class FavoritesInteractor {
    @Inject
    Repository repository;

    @Inject
    Settings settings;

    @Inject
    EventBus bus;

    public FavoritesInteractor() {
        injector.inject(this);
    }

    public void getFavoriteTournaments() {
        GetFavoriteTournamentsEvent event = new GetFavoriteTournamentsEvent();

        try {
            List<Tournament> favorites = new ArrayList<>();

            for (hu.renesans.eredmenyek.model.database.Tournament t : repository.getFavoriteTournaments()) {
                favorites.add(t.toModelObject());
            }

            event.setResult(favorites);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void getFavoriteTeams() {
        GetFavoriteTeamsEvent event = new GetFavoriteTeamsEvent();

        try {
            List<Team> favorites = new ArrayList<>();

            for (hu.renesans.eredmenyek.model.database.Team t : repository.getFavoriteTeams()) {
                favorites.add(t.toModelObject());
            }

            event.setResult(favorites);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void saveFavorite(Tournament tournament) {
        SaveFavoriteTournamentEvent event = new SaveFavoriteTournamentEvent();
        event.setResult(tournament);

        try {
            repository.saveFavorite(tournament.toDbObject());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void saveFavorite(Team team) {
        SaveFavoriteTeamEvent event = new SaveFavoriteTeamEvent();
        event.setResult(team);

        try {
            repository.saveFavorite(team.toDbObject());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void removeFavorite(Tournament tournament) {
        RemoveFavoriteTournamentEvent event = new RemoveFavoriteTournamentEvent();
        event.setResult(tournament);

        try {
            repository.removeFavorite(tournament.toDbObject());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void removeFavorite(Team team) {
        RemoveFavoriteTeamEvent event = new RemoveFavoriteTeamEvent();
        event.setResult(team);

        try {
            repository.removeFavorite(team.toDbObject());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void getShowFavoriteTournamentsOnly() {
        GetShowFavoriteTournamentsOnlyEvent event = new GetShowFavoriteTournamentsOnlyEvent();

        try {
            event.setResult(settings.getShowFavoriteTournamentsOnly());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void setShowFavoriteTournamentsOnly(boolean showFavoritesOnly) {
        SetShowFavoriteTournamentsOnlyEvent event = new SetShowFavoriteTournamentsOnlyEvent();
        event.setResult(showFavoritesOnly);

        try {
            settings.setShowFavoriteTournamentsOnly(showFavoritesOnly);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void getShowFavoriteTeamsOnly() {
        GetShowFavoriteTeamsOnlyEvent event = new GetShowFavoriteTeamsOnlyEvent();

        try {
            event.setResult(settings.getShowFavoriteTeamsOnly());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    public void setShowFavoriteTeamsOnly(boolean showFavoritesOnly) {
        SetShowFavoriteTeamsOnlyEvent event = new SetShowFavoriteTeamsOnlyEvent();
        event.setResult(showFavoritesOnly);

        try {
            settings.setShowFavoriteTeamsOnly(showFavoritesOnly);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }
}
