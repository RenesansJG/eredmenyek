package hu.renesans.eredmenyek;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import hu.renesans.eredmenyek.repository.Repository;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.utils.Store;
import io.fabric.sdk.android.Fabric;

public class EredmenyekApplication extends Application {
    public static EredmenyekApplicationComponent injector;

    @Inject
    Repository repository;

    public void setInjector(EredmenyekApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());

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
