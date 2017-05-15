package hu.renesans.eredmenyek.ui.teams;

import android.os.Bundle;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.renesans.eredmenyek.EredmenyekApplication;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.ui.items.ItemsFragment;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsFragment extends ItemsFragment<Team> {
    @Inject
    TeamsPresenter presenter;

    private Tracker tracker;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EredmenyekApplication application = (EredmenyekApplication) getActivity().getApplication();
        tracker = application.getDefaultTracker();
    }

    @Override
    public void onStart() {
        super.onStart();

        tracker.setScreenName("Image~TeamsFragment");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
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
