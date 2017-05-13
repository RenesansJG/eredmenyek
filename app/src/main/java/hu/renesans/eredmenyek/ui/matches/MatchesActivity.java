package hu.renesans.eredmenyek.ui.matches;

import android.content.Intent;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.MatchHeader;
import hu.renesans.eredmenyek.ui.details.DetailsActivity;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.utils.AssetHelper.loadImage;

public class MatchesActivity extends AppCompatActivity implements MatchesScreen {
    public static final String EXTRA_TOURNAMENT_ID = "tournamentId";
    public static final String EXTRA_TEAM_ID = "teamId";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_IMAGE_URL = "imageUrl";

    private static final long REFRESH_DELAY = 10000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.matchesRV)
    RecyclerView matchesRV;

    @Inject
    MatchesPresenter presenter;

    private enum Type {
        TOURNAMENT, TEAM
    }

    private Type type;
    private long id;
    private List<MatchHeader> matches;
    private MatchesAdapter adapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        ButterKnife.bind(this);
        injector.inject(this);

        if (getIntent().hasExtra(EXTRA_TOURNAMENT_ID)) {
            type = Type.TOURNAMENT;
            id = getIntent().getLongExtra(EXTRA_TOURNAMENT_ID, 0);
        } else if (getIntent().hasExtra(EXTRA_TEAM_ID)) {
            type = Type.TEAM;
            id = getIntent().getLongExtra(EXTRA_TEAM_ID, 0);
        }

        setToolbar();
        setRecyclerView();

        handler = new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);
        findMatches();
    }

    @Override
    protected void onStop() {
        handler.removeCallbacksAndMessages(null);
        presenter.detachScreen();
        super.onStop();
    }

    private void setToolbar() {
        toolbar.setTitle(getIntent().getStringExtra(EXTRA_NAME));
        loadImage(getIntent().getStringExtra(EXTRA_IMAGE_URL), this, d -> {
            if (d != null) {
                int rightMargin = getResources()
                        .getDimensionPixelOffset(R.dimen.toolbar_logo_right_margin);
                toolbar.setLogo(new InsetDrawable(d, 0, 0,
                        rightMargin, 0));
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        matchesRV.setLayoutManager(layoutManager);

        matches = new ArrayList<>();

        adapter = new MatchesAdapter(matches, this::navigateToDetails);
        matchesRV.setAdapter(adapter);
    }

    private void findMatches() {
        switch (type) {
            case TOURNAMENT:
                presenter.findMatchesByTournament(id);
                break;
            case TEAM:
                presenter.findMatchesByTeam(id);
                break;
        }
    }

    @Override
    public void showMatches(List<MatchHeader> matches) {
        this.matches.clear();
        this.matches.addAll(matches);
        adapter.notifyDataSetChanged();
        handler.postDelayed(this::findMatches, REFRESH_DELAY);
    }

    @Override
    public void showErrorMessage() {
        this.matches.clear();
        adapter.notifyDataSetChanged();
        handler.postDelayed(this::findMatches, REFRESH_DELAY);
    }

    private void navigateToDetails(MatchHeader match) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_MATCH_ID, match.getId());
        startActivity(intent);
    }
}
