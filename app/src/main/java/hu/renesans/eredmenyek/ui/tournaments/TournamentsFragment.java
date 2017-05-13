package hu.renesans.eredmenyek.ui.tournaments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.model.Item;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.ui.ItemsAdapter;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TournamentsFragment extends Fragment implements TournamentsScreen {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tournamentsRV)
    RecyclerView tournamentsRV;

    @Inject
    TournamentsPresenter presenter;

    private Unbinder unbinder;

    private List<Item> items;
    private List<Tournament> favorites;
    private ItemsAdapter adapter;
    private boolean showFavoritesOnly;

    public static TournamentsFragment newInstance() {
        TournamentsFragment fragment = new TournamentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TournamentsFragment() {
        injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tournaments, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tournamentsRV.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        favorites = new ArrayList<>();

        adapter = new ItemsAdapter(items, favorites, item -> ((MainActivity) getActivity())
                .navigateToMatches((Tournament) item), favoriteListener);
        tournamentsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        toolbar.setTitle(R.string.title_tournaments);
        ((MainActivity) getActivity()).setToolbar(toolbar);
        presenter.attachScreen(this);
        presenter.getShowFavoritesOnly();
    }

    @Override
    public void onStop() {
        presenter.detachScreen();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private ItemsAdapter.FavoriteListener favoriteListener = new ItemsAdapter.FavoriteListener() {
        @Override
        public void saveFavorite(Item item) {
            presenter.saveFavorite((Tournament) item);
        }

        @Override
        public void removeFavorite(Item item) {
            presenter.removeFavorite((Tournament) item);
        }
    };

    @Override
    public void showFavoritesOnlyChanged(boolean showFavoritesOnly) {
        this.showFavoritesOnly = showFavoritesOnly;

        if (showFavoritesOnly) {
            presenter.getFavorites();
        } else {
            presenter.getTournaments();
        }
    }

    @Override
    public void showTournaments(List<CategoryWithTournaments> tournaments) {
        if (!showFavoritesOnly) {
            items.clear();

            for (CategoryWithTournaments category : tournaments) {
                items.add(category);
                items.addAll(category.getTournaments());
            }

            presenter.getFavorites();
        }
    }

    @Override
    public void showFavorites(List<Tournament> favorites) {
        if (showFavoritesOnly) {
            items.clear();
            items.addAll(favorites);
        }

        this.favorites.clear();
        this.favorites.addAll(favorites);
        adapter.notifyDataSetChanged();

        toolbar.getMenu().clear();
        toolbar.inflateMenu(showFavoritesOnly ? R.menu.menu_items_star :
                R.menu.menu_items_star_outline);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_favorites) {
                presenter.setShowFavoritesOnly(!showFavoritesOnly);
            }

            return false;
        });
    }

    @Override
    public void favoriteSaved(Tournament tournament) {
        favorites.add(tournament);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void favoriteRemoved(Tournament tournament) {
        if (showFavoritesOnly) {
            items.remove(tournament);
        }

        favorites.remove(tournament);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
    }
}
