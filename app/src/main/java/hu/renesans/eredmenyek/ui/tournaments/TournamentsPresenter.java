package hu.renesans.eredmenyek.ui.tournaments;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.interactor.favorites.FavoritesInteractor;
import hu.renesans.eredmenyek.interactor.favorites.events.GetFavoriteTournamentsEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.GetShowFavoriteTournamentsOnlyEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.RemoveFavoriteTournamentEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SaveFavoriteTournamentEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SetShowFavoriteTournamentsOnlyEvent;
import hu.renesans.eredmenyek.interactor.tournaments.TournamentsInteractor;
import hu.renesans.eredmenyek.interactor.tournaments.event.GetTournamentsEvent;
import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.ui.items.ItemsPresenter;
import hu.renesans.eredmenyek.ui.items.ItemsScreen;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TournamentsPresenter extends ItemsPresenter<Tournament> {
    @Inject
    TournamentsInteractor tournamentsInteractor;

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    private List<CategoryWithTournaments> tournamentsCache;
    private List<Tournament> favoritesCache;
    private boolean showFavoritesOnly;

    @Override
    public void attachScreen(ItemsScreen<Tournament> screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);

        screen.showFavoritesOnlyChanged(showFavoritesOnly);

        if (tournamentsCache != null) {
            screen.showItems(tournamentsCache);
        }

        if (favoritesCache != null) {
            screen.showFavorites(favoritesCache);
        }
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    @Override
    public void getItems() {
        executor.execute(() -> tournamentsInteractor.getTournaments());
    }

    @Override
    public void getFavorites() {
        executor.execute(() -> favoritesInteractor.getFavoriteTournaments());
    }

    @Override
    public void saveFavorite(Tournament tournament) {
        executor.execute(() -> favoritesInteractor.saveFavorite(tournament));
    }

    @Override
    public void removeFavorite(Tournament tournament) {
        executor.execute(() -> favoritesInteractor.removeFavorite(tournament));
    }

    @Override
    public void getShowFavoritesOnly() {
        executor.execute(() -> favoritesInteractor.getShowFavoriteTournamentsOnly());
    }

    @Override
    public void setShowFavoritesOnly(boolean showFavoritesOnly) {
        executor.execute(() -> favoritesInteractor.setShowFavoriteTournamentsOnly(showFavoritesOnly));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetTournamentsEvent(GetTournamentsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_tournaments);
            Log.e("Networking", "Error getting tournaments", event.getThrowable());
        } else {
            if (screen != null) screen.showItems(event.getResult());
            tournamentsCache = event.getResult();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetFavoriteTournamentsEvent(GetFavoriteTournamentsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_favorites);
            Log.e("Database", "Error getting favorite tournaments", event.getThrowable());
        } else {
            if (screen != null) screen.showFavorites(event.getResult());
            favoritesCache = event.getResult();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveFavoriteTournamentEvent(SaveFavoriteTournamentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_save_favorite);
            Log.e("Database", "Error saving favorite tournament", event.getThrowable());
        } else {
            if (screen != null) screen.favoriteSaved(event.getResult());
            if (favoritesCache != null) favoritesCache.add(event.getResult());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRemoveFavoriteTournamentEvent(RemoveFavoriteTournamentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_remove_favorite);
            Log.e("Database", "Error removing favorite tournament", event.getThrowable());
        } else {
            if (screen != null) screen.favoriteRemoved(event.getResult());
            if (favoritesCache != null) favoritesCache.remove(event.getResult());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetShowFavoriteTournamentsOnlyEvent(GetShowFavoriteTournamentsOnlyEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_setting);
            Log.e("Store", "Error getting \"show favorite tournaments only\" flag", event.getThrowable());
        } else {
            if (event.getResult() != showFavoritesOnly) {
                if (screen != null) screen.showFavoritesOnlyChanged(event.getResult());
                showFavoritesOnly = event.getResult();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetShowFavoriteTournamentsOnlyEvent(SetShowFavoriteTournamentsOnlyEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_set_setting);
            Log.e("Store", "Error setting \"show favorite tournaments only\" flag", event.getThrowable());
        } else {
            if (event.getResult() != showFavoritesOnly) {
                if (screen != null) screen.showFavoritesOnlyChanged(event.getResult());
                showFavoritesOnly = event.getResult();
            }
        }
    }
}
