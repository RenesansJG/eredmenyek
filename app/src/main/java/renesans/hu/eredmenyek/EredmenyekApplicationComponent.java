package renesans.hu.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import renesans.hu.eredmenyek.ui.UIModule;
import renesans.hu.eredmenyek.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface EredmenyekApplicationComponent {
    void inject(MainActivity mainActivity);
}
