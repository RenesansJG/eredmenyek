package renesans.hu.eredmenyek.ui.tournaments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import renesans.hu.eredmenyek.R;

import static renesans.hu.eredmenyek.EredmenyekApplication.injector;

public class TournamentsActivity extends AppCompatActivity implements TournamentsScreen {
    @Inject
    TournamentsPresenter tournamentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournaments);

        // inject the dependencies
        injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tournamentsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        tournamentsPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
