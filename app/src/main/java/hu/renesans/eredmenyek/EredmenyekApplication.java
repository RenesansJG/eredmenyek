package hu.renesans.eredmenyek;

import android.app.Application;

import javax.inject.Inject;

import hu.renesans.eredmenyek.repository.Repository;
import hu.renesans.eredmenyek.ui.UIModule;

public class EredmenyekApplication extends Application {
    public static EredmenyekApplicationComponent injector;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerEredmenyekApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
