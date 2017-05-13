package hu.renesans.eredmenyek.ui.tournaments;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.model.Tournament;

public interface TournamentsScreen {
    void showTournaments(List<CategoryWithTournaments> tournaments);

    void showFavorites(List<Tournament> favorites);

    void favoriteSaved(Tournament tournament);

    void favoriteRemoved(Tournament tournament);

    void showFavoritesOnlyChanged(boolean showFavoritesOnly);

    void showErrorMessage();
}
