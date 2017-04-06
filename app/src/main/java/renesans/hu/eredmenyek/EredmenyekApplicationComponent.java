package renesans.hu.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import renesans.hu.eredmenyek.ui.UIModule;
import renesans.hu.eredmenyek.ui.teams.TeamsActivity;
import renesans.hu.eredmenyek.ui.tournaments.TournamentsActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface EredmenyekApplicationComponent {
    void inject(TournamentsActivity tournamentsActivity);
    void inject(TeamsActivity teamsActivity);
}
