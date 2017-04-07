package hu.renesans.eredmenyek.ui.matches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class MatchesActivity extends AppCompatActivity implements MatchesScreen {
    @Inject
    MatchesPresenter matchesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        matchesPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        matchesPresenter.detachScreen();
        super.onStop();
    }
}
