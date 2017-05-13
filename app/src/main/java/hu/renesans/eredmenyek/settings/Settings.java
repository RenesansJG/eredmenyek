package hu.renesans.eredmenyek.settings;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.renesans.eredmenyek.utils.Store;

@Singleton
public class Settings {
    private static final String SHOW_FAVORITE_TOURNAMENTS_ONLY = "showFavoriteTournamentsOnly";
    private static final String SHOW_FAVORITE_TEAMS_ONLY = "showFavoriteTeamsOnly";

    @Inject
    public Settings() {
    }

    public boolean getShowFavoriteTournamentsOnly() {
        Boolean b = Store.get(SHOW_FAVORITE_TOURNAMENTS_ONLY, Boolean.TYPE);
        return (b != null) ? b : false;
    }

    public void setShowFavoriteTournamentsOnly(boolean showFavoritesOnly) {
        Store.put(SHOW_FAVORITE_TOURNAMENTS_ONLY, showFavoritesOnly);
    }

    public boolean getShowFavoriteTeamsOnly() {
        Boolean b = Store.get(SHOW_FAVORITE_TEAMS_ONLY, Boolean.TYPE);
        return (b != null) ? b : false;
    }

    public void setShowFavoriteTeamsOnly(boolean showFavoritesOnly) {
        Store.put(SHOW_FAVORITE_TEAMS_ONLY, showFavoritesOnly);
    }
}
