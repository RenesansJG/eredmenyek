package hu.renesans.eredmenyek;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.renesans.eredmenyek.repository.Repository;
import hu.renesans.eredmenyek.ui.UIModule;
import hu.renesans.eredmenyek.utils.Store;
import io.fabric.sdk.android.Fabric;

public class EredmenyekApplication extends Application {
    public static EredmenyekApplicationComponent injector;

    private Tracker tracker;

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

    public synchronized Tracker getDefaultTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            tracker = analytics.newTracker(R.xml.global_tracker);
        }

        return tracker;
    }
}
