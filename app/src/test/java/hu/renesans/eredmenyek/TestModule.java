package hu.renesans.eredmenyek;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.ui.details.DetailsPresenter;
import hu.renesans.eredmenyek.ui.matches.MatchesPresenter;
import hu.renesans.eredmenyek.ui.teams.TeamsPresenter;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsPresenter;
import hu.renesans.eredmenyek.utils.UiExecutor;

@Module
public class TestModule {
    private final UIModule uiModule;

    public TestModule(Context context) {
        uiModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return uiModule.provideContext();
    }

    @Provides
    public TournamentsPresenter provideTournamentsPresenter() {
        return uiModule.provideTournamentsPresenter();
    }

    @Provides
    public TeamsPresenter provideTeamsPresenter() {
        return uiModule.provideTeamsPresenter();
    }

    @Provides
    public MatchesPresenter provideMatchesPresenter() {
        return uiModule.provideMatchesPresenter();
    }

    @Provides
    public DetailsPresenter provideDetailsPresenter() {
        return uiModule.provideDetailsPresenter();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return new UiExecutor();
    }
}
