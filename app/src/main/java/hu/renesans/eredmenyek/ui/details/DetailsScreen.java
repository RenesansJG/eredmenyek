package hu.renesans.eredmenyek.ui.details;

import android.support.annotation.StringRes;

import hu.renesans.eredmenyek.model.Match;

public interface DetailsScreen {
    void showMatch(Match match);

    void showErrorMessage(@StringRes int messageId);
}
