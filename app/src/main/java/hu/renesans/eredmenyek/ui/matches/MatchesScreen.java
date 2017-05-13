package hu.renesans.eredmenyek.ui.matches;

import java.util.List;

import hu.renesans.eredmenyek.model.MatchHeader;

public interface MatchesScreen {
    void showMatches(List<MatchHeader> matches);

    void showErrorMessage();
}
