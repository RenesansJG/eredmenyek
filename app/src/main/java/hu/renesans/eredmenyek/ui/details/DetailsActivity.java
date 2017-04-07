package hu.renesans.eredmenyek.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import hu.renesans.eredmenyek.R;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {
    @Inject
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        detailsPresenter.detachScreen();
        super.onStop();
    }
}
