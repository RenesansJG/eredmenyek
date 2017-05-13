package hu.renesans.eredmenyek;

import android.app.Application;

import javax.inject.Inject;

import hu.renesans.eredmenyek.repository.Repository;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.utils.Store;

public class EredmenyekApplication extends Application {
    public static EredmenyekApplicationComponent injector;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.MOCK) {
            injector = DaggerMockEredmenyekApplicationComponent.builder()
                    .uIModule(new UIModule(this))
                    .build();
        } else {
            injector = DaggerEredmenyekApplicationComponent.builder()
                    .uIModule(new UIModule(this))
                    .build();
        }


        injector.inject(this);
        Store.init(this);
        repository.open(getApplicationContext());
    }
}
