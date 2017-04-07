package hu.renesans.eredmenyek.ui.tournaments;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.favorites.events.AddFavoriteTournamentEvent;
import hu.renesans.eredmenyek.ui.Presenter;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TournamentsPresenter extends Presenter<TournamentsScreen> {
    @Inject
    EventBus bus;

    @Override
    public void attachScreen(TournamentsScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddFavoriteTournamentEvent(AddFavoriteTournamentEvent event) {
    }
}
