package hu.renesans.eredmenyek.ui.teams;

import android.os.Bundle;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.ui.items.ItemsFragment;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsFragment extends ItemsFragment<Team> {
    @Inject
    TeamsPresenter presenter;

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
    protected TeamsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_teams;
    }

    @Override
    protected void onItemClick(Team item) {
        ((MainActivity) getActivity()).navigateToMatches(item);
    }
}
