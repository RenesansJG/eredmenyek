package hu.renesans.eredmenyek.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.renesans.eredmenyek.R;
import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.ui.BaseActivity;
import hu.renesans.eredmenyek.ui.matches.MatchesActivity;
import hu.renesans.eredmenyek.ui.teams.TeamsFragment;
import hu.renesans.eredmenyek.ui.tournaments.TournamentsFragment;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class MainActivity extends BaseActivity {
    private static final String TOURNAMENTS = "tournaments";
    private static final String TEAMS = "teams";
    private static final long NAVIGATION_DELAY = 500L;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.navigation)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injector.inject(this);

        setNavigationView();
        navigateToScreen(TOURNAMENTS);
    }

    public void setToolbar(Toolbar toolbar) {
        if (drawerToggle != null) drawerLayout.removeDrawerListener(drawerToggle);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();

            Handler handler = new Handler();

            switch (item.getItemId()) {
                case R.id.tournaments:
                    handler.postDelayed(() -> navigateToScreen(TOURNAMENTS), NAVIGATION_DELAY);
                    break;
                case R.id.teams:
                    handler.postDelayed(() -> navigateToScreen(TEAMS), NAVIGATION_DELAY);
                    break;
                default:
                    break;
            }

            return true;
        });
    }

    private void navigateToScreen(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);

        if (fragment == null) {
            switch (tag) {
                case TOURNAMENTS:
                    fragment = TournamentsFragment.newInstance();
                    break;
                case TEAMS:
                    fragment = TeamsFragment.newInstance();
                    break;
                default:
                    fragment = TournamentsFragment.newInstance();
                    break;
            }
        }

        fm.beginTransaction().replace(R.id.container, fragment, tag).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(Gravity.START);
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void navigateToMatches(String idKey, long id, String name, String imageUrl) {
        Intent intent = new Intent(this, MatchesActivity.class);
        intent.putExtra(idKey, id);
        intent.putExtra(MatchesActivity.EXTRA_NAME, name);
        intent.putExtra(MatchesActivity.EXTRA_IMAGE_URL, imageUrl);
        startActivity(intent);
    }

    public void navigateToMatches(Tournament tournament) {
        navigateToMatches(MatchesActivity.EXTRA_TOURNAMENT_ID, tournament.getId(),
                tournament.getName(), tournament.getImageUrl());
    }

    public void navigateToMatches(Team team) {
        navigateToMatches(MatchesActivity.EXTRA_TEAM_ID, team.getId(),
                team.getName(), team.getImageUrl());
    }
}
