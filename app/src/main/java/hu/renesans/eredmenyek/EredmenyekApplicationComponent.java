package hu.renesans.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import hu.renesans.eredmenyek.interactor.InteractorModule;
import hu.renesans.eredmenyek.interactor.favorites.FavoritesInteractor;
import hu.renesans.eredmenyek.interactor.matches.MatchesInteractor;
import hu.renesans.eredmenyek.interactor.teams.TeamsInteractor;
import hu.renesans.eredmenyek.interactor.tournaments.TournamentsInteractor;
import hu.renesans.eredmenyek.mock.interceptors.MockInterceptor;
import hu.renesans.eredmenyek.network.NetworkModule;
import hu.renesans.eredmenyek.repository.RepositoryModule;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.ui.details.DetailsActivity;
import hu.renesans.eredmenyek.ui.details.DetailsPresenter;
import hu.renesans.eredmenyek.ui.main.MainActivity;
import hu.renesans.eredmenyek.ui.matches.MatchesActivity;
import hu.renesans.eredmenyek.ui.matches.MatchesPresenter;
import hu.renesans.eredmenyek.ui.teams.TeamsFragment;
import hu.renesans.eredmenyek.ui.teams.TeamsPresenter;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsFragment;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsPresenter;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, NetworkModule.class})
public interface EredmenyekApplicationComponent {
    void inject(EredmenyekApplication eredmenyekApplication);

    void inject(MainActivity mainActivity);

    void inject(MatchesActivity matchesActivity);

    void inject(DetailsActivity detailsActivity);

    void inject(TournamentsFragment tournamentsFragment);

    void inject(TeamsFragment teamsFragment);

    void inject(TournamentsPresenter tournamentsPresenter);

    void inject(TeamsPresenter teamsPresenter);

    void inject(MatchesPresenter matchesPresenter);

    void inject(DetailsPresenter detailsPresenter);

    void inject(TournamentsInteractor tournamentsInteractor);

    void inject(TeamsInteractor teamsInteractor);

    void inject(MatchesInteractor matchesInteractor);

    void inject(FavoritesInteractor favoritesInteractor);

    void inject(MockInterceptor mockInterceptor);
}
