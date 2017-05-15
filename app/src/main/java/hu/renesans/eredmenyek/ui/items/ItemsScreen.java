package hu.renesans.eredmenyek.ui.items;

import android.support.annotation.StringRes;

import java.util.List;

import hu.renesans.eredmenyek.model.Category;
import hu.renesans.eredmenyek.model.Item;

public interface ItemsScreen<T extends Item<T>> {
    void showItems(List<? extends Category<T>> items);

    void showFavorites(List<T> favorites);

    void favoriteSaved(T item);

    void favoriteRemoved(T item);

    void showFavoritesOnlyChanged(boolean showFavoritesOnly);

    void showErrorMessage(@StringRes int messageId);
}
