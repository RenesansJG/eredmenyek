package hu.renesans.eredmenyek.ui.details;

import hu.renesans.eredmenyek.model.Match;

public interface DetailsScreen {
    void showMatch(Match match);

    void showErrorMessage();
}
