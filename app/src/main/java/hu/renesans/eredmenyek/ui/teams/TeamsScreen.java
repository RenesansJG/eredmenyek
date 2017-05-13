package hu.renesans.eredmenyek.ui.teams;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.model.Team;

public interface TeamsScreen {
    void showTeams(List<CategoryWithTeams> teams);

    void showFavorites(List<Team> favorites);

    void favoriteSaved(Team team);

    void favoriteRemoved(Team team);

    void showFavoritesOnlyChanged(boolean showFavoritesOnly);

    void showErrorMessage();
}
