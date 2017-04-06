package renesans.hu.eredmenyek.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import renesans.hu.eredmenyek.ui.tournaments.TournamentsPresenter;

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
}
