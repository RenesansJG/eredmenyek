package renesans.hu.eredmenyek;

import android.app.Application;

import renesans.hu.eredmenyek.ui.UIModule;

public class EredmenyekApplication extends Application {
    public static EredmenyekApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerEredmenyekApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .build();
    }
}
