package hu.renesans.eredmenyek.ui;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    @Singleton
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
    public MatchesPresenter provideMatchesPresenter() {
        return new MatchesPresenter();
    }

    @Provides
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
