package hu.renesans.eredmenyek.ui.teams;

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
import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.model.Item;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.ui.ItemsAdapter;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsFragment extends Fragment implements TeamsScreen {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.teamsRV)
    RecyclerView teamsRV;

    @Inject
    TeamsPresenter presenter;

    private Unbinder unbinder;

    private List<Item> items;
    private List<Team> favorites;
    private ItemsAdapter adapter;
    private boolean showFavoritesOnly;

    public static TeamsFragment newInstance() {
        TeamsFragment fragment = new TeamsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TeamsFragment() {
        injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        teamsRV.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        favorites = new ArrayList<>();

        adapter = new ItemsAdapter(items, favorites, item -> ((MainActivity) getActivity())
                .navigateToMatches((Team) item), favoriteListener);
        teamsRV.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        toolbar.setTitle(R.string.title_teams);
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
            presenter.saveFavorite((Team) item);
        }

        @Override
        public void removeFavorite(Item item) {
            presenter.removeFavorite((Team) item);
        }
    };

    @Override
    public void showFavoritesOnlyChanged(boolean showFavoritesOnly) {
        this.showFavoritesOnly = showFavoritesOnly;

        if (showFavoritesOnly) {
            presenter.getFavorites();
        } else {
            presenter.getTeams();
        }
    }

    @Override
    public void showTeams(List<CategoryWithTeams> teams) {
        if (!showFavoritesOnly) {
            items.clear();

            for (CategoryWithTeams category : teams) {
                items.add(category);
                items.addAll(category.getTeams());
            }

            presenter.getFavorites();
        }
    }

    @Override
    public void showFavorites(List<Team> favorites) {
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
    public void favoriteSaved(Team team) {
        favorites.add(team);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void favoriteRemoved(Team team) {
        if (showFavoritesOnly) {
            items.remove(team);
        }

        favorites.remove(team);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {
    }
}
