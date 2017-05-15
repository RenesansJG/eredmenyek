package hu.renesans.eredmenyek.ui.items;

import hu.renesans.eredmenyek.model.Item;
import hu.renesans.eredmenyek.ui.Presenter;

public abstract class ItemsPresenter<T extends Item<T>> extends Presenter<ItemsScreen<T>> {
    public abstract void getItems();

    public abstract void getFavorites();

    public abstract void saveFavorite(T item);

    public abstract void removeFavorite(T item);

    public abstract void getShowFavoritesOnly();

    public abstract void setShowFavoritesOnly(boolean showFavoritesOnly);
}
