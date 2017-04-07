package hu.renesans.eredmenyek.ui.teams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.favorites.events.AddFavoriteTeamEvent;
import hu.renesans.eredmenyek.ui.Presenter;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsPresenter extends Presenter<TeamsScreen> {
    @Inject
    EventBus bus;

    @Override
    public void attachScreen(TeamsScreen screen) {
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
    public void onAddFavoriteTeamEvent(AddFavoriteTeamEvent event) {
    }
}
