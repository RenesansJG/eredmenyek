package hu.renesans.eredmenyek.ui.details;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.EredmenyekApplication;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Match;
import hu.renesans.eredmenyek.ui.BaseActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.utils.AssetHelper.loadImage;
import static hu.renesans.eredmenyek.utils.MinuteFormatter.formatMinute;

public class DetailsActivity extends BaseActivity implements DetailsScreen {
    public static final String EXTRA_MATCH_ID = "matchId";

    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault());

    private static final long REFRESH_DELAY = 10000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.scoreTV)
    TextView scoreTV;

    @BindView(R.id.homeTeamIV)
    ImageView homeTeamIV;

    @BindView(R.id.awayTeamIV)
    ImageView awayTeamIV;

    @BindView(R.id.homeTeamTV)
    TextView homeTeamTV;

    @BindView(R.id.awayTeamTV)
    TextView awayTeamTV;

    @BindView(R.id.dateTimeTV)
    TextView dateTimeTV;

    @BindView(R.id.minuteTV)
    TextView minuteTV;

    @BindView(R.id.eventsRV)
    RecyclerView eventsRV;

    @Inject
    DetailsPresenter presenter;

    private int id;
    private EventsAdapter adapter;
    private Handler handler;

    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        injector.inject(this);

        id = getIntent().getIntExtra(EXTRA_MATCH_ID, 0);

        setToolbar();
        setRecyclerView();

        handler = new Handler();

        EredmenyekApplication application = (EredmenyekApplication) getApplication();
        tracker = application.getDefaultTracker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);
        presenter.getMatch(id);

        tracker.setScreenName("Image~DetailsActivity");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        handler.removeCallbacksAndMessages(null);
        presenter.detachScreen();
        super.onStop();
    }

    private void setToolbar() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventsRV.setLayoutManager(layoutManager);

        adapter = new EventsAdapter();
        eventsRV.setAdapter(adapter);
    }

    @Override
    public void showMatch(Match match) {
        scoreTV.setText(getString(R.string.format_score,
                match.getHomeScore() != null ? Integer.toString(match.getHomeScore()) : "",
                match.getAwayScore() != null ? Integer.toString(match.getAwayScore()) : ""));
        scoreTV.setTextColor(ContextCompat.getColor(this,
                match.isStarted() ? R.color.live_text : R.color.primary_text));
        loadImage(match.getHomeTeam().getImageUrl(), homeTeamIV);
        loadImage(match.getAwayTeam().getImageUrl(), awayTeamIV);
        homeTeamTV.setText(match.getHomeTeam().getName());
        awayTeamTV.setText(match.getAwayTeam().getName());
        homeTeamTV.setTypeface(null, !match.isStarted() &&
                match.getHomeScore() != null && match.getAwayScore() != null &&
                match.getHomeScore() > match.getAwayScore() ? Typeface.BOLD : Typeface.NORMAL);
        awayTeamTV.setTypeface(null, !match.isStarted() &&
                match.getHomeScore() != null && match.getAwayScore() != null &&
                match.getHomeScore() < match.getAwayScore() ? Typeface.BOLD : Typeface.NORMAL);
        dateTimeTV.setText(DATE_FORMAT.format(match.getStartTime()));

        if (match.isStarted()) {
            minuteTV.setText(formatMinute(this, match.getPeriod(), match.getMinute(), true));
        }

        minuteTV.setVisibility(match.isStarted() ? VISIBLE : GONE);

        adapter.setEvents(match.getEvents());
        handler.postDelayed(() -> presenter.getMatch(id), REFRESH_DELAY);
    }

    @Override
    public void showErrorMessage(int messageId) {
        showSnackbar(getString(messageId));
        adapter.clearEvents();
        handler.postDelayed(() -> presenter.getMatch(id), REFRESH_DELAY);
    }
}
