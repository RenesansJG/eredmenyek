package hu.renesans.eredmenyek.ui.matches;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.matches.MatchesInteractor;
import hu.renesans.eredmenyek.interactor.matches.event.FindMatchesByTeamEvent;
import hu.renesans.eredmenyek.interactor.matches.event.FindMatchesByTournamentEvent;
import hu.renesans.eredmenyek.model.MatchHeader;
import hu.renesans.eredmenyek.ui.Presenter;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class MatchesPresenter extends Presenter<MatchesScreen> {
    @Inject
    MatchesInteractor matchesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    private List<MatchHeader> matchesCache;

    @Override
    public void attachScreen(MatchesScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);

        if (matchesCache != null) {
            if (screen != null) screen.showMatches(matchesCache);
        }
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void findMatchesByTournament(long id) {
        executor.execute(() -> matchesInteractor.findMatchesByTournament(id));
    }

    public void findMatchesByTeam(long id) {
        executor.execute(() -> matchesInteractor.findMatchesByTeam(id));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFindMatchesByTournamentEvent(FindMatchesByTournamentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage();
            Log.e("Networking", "Error finding matches by tournament", event.getThrowable());
        } else {
            if (screen != null) screen.showMatches(event.getResult());
            matchesCache = event.getResult();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFindMatchesByTeamEvent(FindMatchesByTeamEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage();
            Log.e("Networking", "Error finding matches by team", event.getThrowable());
        } else {
            if (screen != null) screen.showMatches(event.getResult());
        }
    }
}
