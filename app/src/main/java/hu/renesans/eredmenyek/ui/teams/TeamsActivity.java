package hu.renesans.eredmenyek.ui.teams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class TeamsActivity extends AppCompatActivity implements TeamsScreen {
    @Inject
    TeamsPresenter teamsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        teamsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        teamsPresenter.detachScreen();
        super.onStop();
    }
}
