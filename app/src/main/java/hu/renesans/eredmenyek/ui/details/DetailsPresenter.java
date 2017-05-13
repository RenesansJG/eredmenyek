package hu.renesans.eredmenyek.ui.details;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.matches.MatchesInteractor;
import hu.renesans.eredmenyek.interactor.matches.event.GetMatchEvent;
import hu.renesans.eredmenyek.model.Match;
import hu.renesans.eredmenyek.ui.Presenter;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class DetailsPresenter extends Presenter<DetailsScreen> {
    @Inject
    MatchesInteractor matchesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    private Match matchCache;

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);

        if (matchCache != null) {
            if (screen != null) screen.showMatch(matchCache);
        }
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getMatch(int id) {
        executor.execute(() -> matchesInteractor.getMatch(id));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMatchEvent(GetMatchEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage();
            Log.e("Networking", "Error getting match", event.getThrowable());
        } else {
            if (screen != null) screen.showMatch(event.getResult());
            matchCache = event.getResult();
        }
    }
}
