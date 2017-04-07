package hu.renesans.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import hu.renesans.eredmenyek.repository.RepositoryModule;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.ui.details.DetailsActivity;
import hu.renesans.eredmenyek.ui.matches.MatchesActivity;
import hu.renesans.eredmenyek.ui.teams.TeamsActivity;
import hu.renesans.eredmenyek.ui.teams.TeamsPresenter;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsActivity;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsPresenter;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class})
public interface EredmenyekApplicationComponent {
    void inject(EredmenyekApplication eredmenyekApplication);

    void inject(TournamentsActivity tournamentsActivity);
    void inject(TeamsActivity teamsActivity);
    void inject(MatchesActivity matchesActivity);
    void inject(DetailsActivity detailsActivity);

    void inject(TournamentsPresenter tournamentsPresenter);
    void inject(TeamsPresenter teamsPresenter);
}
