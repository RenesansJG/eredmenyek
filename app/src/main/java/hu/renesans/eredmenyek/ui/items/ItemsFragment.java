package hu.renesans.eredmenyek.ui.items;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Category;
import hu.renesans.eredmenyek.model.Item;
import hu.renesans.eredmenyek.ui.main.MainActivity;

public abstract class ItemsFragment<T extends Item<T>> extends Fragment implements ItemsScreen<T> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.itemsRV)
    RecyclerView itemsRV;

    private Unbinder unbinder;

    private ItemsAdapter<T> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemsRV.setLayoutManager(layoutManager);

        //noinspection unchecked
        adapter = new ItemsAdapter<>(item -> onItemClick((T) item), favoriteListener);
        itemsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        toolbar.setTitle(getTitleId());
        ((MainActivity) getActivity()).setToolbar(toolbar);

        getPresenter().attachScreen(this);
        getPresenter().getShowFavoritesOnly();
        getPresenter().getItems();
        getPresenter().getFavorites();
    }

    @Override
    public void onStop() {
        getPresenter().detachScreen();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private ItemsAdapter.FavoriteListener<T> favoriteListener = new ItemsAdapter.FavoriteListener<T>() {
        @SuppressWarnings("unchecked")
        @Override
        public void saveFavorite(Item<T> item) {
            getPresenter().saveFavorite((T) item);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void removeFavorite(Item<T> item) {
            getPresenter().removeFavorite((T) item);
        }
    };

    protected abstract ItemsPresenter<T> getPresenter();

    @StringRes
    protected abstract int getTitleId();

    protected abstract void onItemClick(T item);

    @Override
    public void showItems(List<? extends Category<T>> items) {
        adapter.setItems(items);
    }

    @Override
    public void showFavorites(List<T> favorites) {
        adapter.setFavorites(favorites);
    }

    @Override
    public void favoriteSaved(T item) {
        adapter.addFavorite(item);
    }

    @Override
    public void favoriteRemoved(T item) {
        adapter.removeFavorite(item);
    }

    @Override
    public void showFavoritesOnlyChanged(boolean showFavoritesOnly) {
        adapter.setShowFavoritesOnly(showFavoritesOnly);

        toolbar.getMenu().clear();
        toolbar.inflateMenu(showFavoritesOnly ? R.menu.menu_items_star :
                R.menu.menu_items_star_outline);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_favorites) {
                getPresenter().setShowFavoritesOnly(!showFavoritesOnly);
            }

            return false;
        });
    }

    @Override
    public void showErrorMessage(int messageId) {
        ((MainActivity) getActivity()).showSnackbar(getString(messageId));
    }
}
