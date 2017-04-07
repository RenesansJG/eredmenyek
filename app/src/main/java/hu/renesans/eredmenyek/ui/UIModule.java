package hu.renesans.eredmenyek.ui;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.renesans.eredmenyek.ui.details.DetailsPresenter;
import hu.renesans.eredmenyek.ui.matches.MatchesPresenter;
import hu.renesans.eredmenyek.ui.teams.TeamsPresenter;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsPresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public TournamentsPresenter provideTournamentsPresenter() {
        return new TournamentsPresenter();
    }

    @Provides
    @Singleton
    public TeamsPresenter provideTeamsPresenter() {
        return new TeamsPresenter();
    }

    @Provides
    @Singleton
    public MatchesPresenter provideMatchesPresenter() {
        return new MatchesPresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
