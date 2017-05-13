package hu.renesans.eredmenyek.interactor;

import dagger.Module;
import dagger.Provides;
import hu.renesans.eredmenyek.interactor.favorites.FavoritesInteractor;
import hu.renesans.eredmenyek.interactor.matches.MatchesInteractor;
import hu.renesans.eredmenyek.interactor.teams.TeamsInteractor;
import hu.renesans.eredmenyek.interactor.tournaments.TournamentsInteractor;

@Module
public class InteractorModule {
    @Provides
    public TournamentsInteractor provideTournaments() {
        return new TournamentsInteractor();
    }

    @Provides
    public TeamsInteractor provideTeams() {
        return new TeamsInteractor();
    }

    @Provides
    public MatchesInteractor provideMatches() {
        return new MatchesInteractor();
    }

    @Provides
    public FavoritesInteractor provideFavorites() {
        return new FavoritesInteractor();
    }
}
