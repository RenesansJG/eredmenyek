package hu.renesans.eredmenyek.ui.teams;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.interactor.favorites.FavoritesInteractor;
import hu.renesans.eredmenyek.interactor.favorites.events.GetFavoriteTeamsEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.GetShowFavoriteTeamsOnlyEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.RemoveFavoriteTeamEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SaveFavoriteTeamEvent;
import hu.renesans.eredmenyek.interactor.favorites.events.SetShowFavoriteTeamsOnlyEvent;
import hu.renesans.eredmenyek.interactor.teams.TeamsInteractor;
import hu.renesans.eredmenyek.interactor.teams.event.GetTeamsEvent;
import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.ui.items.ItemsPresenter;
import hu.renesans.eredmenyek.ui.items.ItemsScreen;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsPresenter extends ItemsPresenter<Team> {
    @Inject
    TeamsInteractor teamsInteractor;

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    private List<CategoryWithTeams> teamsCache;
    private List<Team> favoritesCache;
    private boolean showFavoritesOnly;

    @Override
    public void attachScreen(ItemsScreen<Team> screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);

        screen.showFavoritesOnlyChanged(showFavoritesOnly);

        if (teamsCache != null) {
            screen.showItems(teamsCache);
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
        executor.execute(() -> teamsInteractor.getTeams());
    }

    @Override
    public void getFavorites() {
        executor.execute(() -> favoritesInteractor.getFavoriteTeams());
    }

    @Override
    public void saveFavorite(Team team) {
        executor.execute(() -> favoritesInteractor.saveFavorite(team));
    }

    @Override
    public void removeFavorite(Team team) {
        executor.execute(() -> favoritesInteractor.removeFavorite(team));
    }

    @Override
    public void getShowFavoritesOnly() {
        executor.execute(() -> favoritesInteractor.getShowFavoriteTeamsOnly());
    }

    @Override
    public void setShowFavoritesOnly(boolean showFavoritesOnly) {
        executor.execute(() -> favoritesInteractor.setShowFavoriteTeamsOnly(showFavoritesOnly));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetTeamsEvent(GetTeamsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_teams);
            Log.e("Networking", "Error getting teams", event.getThrowable());
        } else {
            if (screen != null) screen.showItems(event.getResult());
            teamsCache = event.getResult();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetFavoriteTeamsEvent(GetFavoriteTeamsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_favorites);
            Log.e("Database", "Error getting favorite teams", event.getThrowable());
        } else {
            if (screen != null) screen.showFavorites(event.getResult());
            favoritesCache = event.getResult();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveFavoriteTeamEvent(SaveFavoriteTeamEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_save_favorite);
            Log.e("Database", "Error saving favorite team", event.getThrowable());
        } else {
            if (screen != null) screen.favoriteSaved(event.getResult());
            if (favoritesCache != null) favoritesCache.add(event.getResult());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRemoveFavoriteTeamEvent(RemoveFavoriteTeamEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_remove_favorite);
            Log.e("Database", "Error removing favorite team", event.getThrowable());
        } else {
            if (screen != null) screen.favoriteRemoved(event.getResult());
            if (favoritesCache != null) favoritesCache.remove(event.getResult());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetShowFavoriteTeamsOnlyEvent(GetShowFavoriteTeamsOnlyEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_get_setting);
            Log.e("Store", "Error getting \"show favorite teams only\" flag", event.getThrowable());
        } else {
            if (event.getResult() != showFavoritesOnly) {
                if (screen != null) screen.showFavoritesOnlyChanged(event.getResult());
                showFavoritesOnly = event.getResult();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSetShowFavoriteTeamsOnlyEvent(SetShowFavoriteTeamsOnlyEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) screen.showErrorMessage(R.string.error_set_setting);
            Log.e("Store", "Error setting \"show favorite teams only\" flag", event.getThrowable());
        } else {
            if (event.getResult() != showFavoritesOnly) {
                if (screen != null) screen.showFavoritesOnlyChanged(event.getResult());
                showFavoritesOnly = event.getResult();
            }
        }
    }
}
