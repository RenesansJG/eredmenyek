package hu.renesans.eredmenyek.ui.tournaments;

import android.os.Bundle;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.ui.items.ItemsFragment;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TournamentsFragment extends ItemsFragment<Tournament> {
    @Inject
    TournamentsPresenter presenter;

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
    protected TournamentsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_tournaments;
    }

    @Override
    protected void onItemClick(Tournament item) {
        ((MainActivity) getActivity()).navigateToMatches(item);
    }
}
