package hu.renesans.eredmenyek.ui.tournaments;

import android.os.Bundle;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.renesans.eredmenyek.EredmenyekApplication;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.ui.items.ItemsFragment;
import hu.renesans.eredmenyek.ui.main.MainActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TournamentsFragment extends ItemsFragment<Tournament> {
    @Inject
    TournamentsPresenter presenter;

    private Tracker tracker;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EredmenyekApplication application = (EredmenyekApplication) getActivity().getApplication();
        tracker = application.getDefaultTracker();
    }

    @Override
    public void onStart() {
        super.onStart();

        tracker.setScreenName("Image~TournamentsFragment");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
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
