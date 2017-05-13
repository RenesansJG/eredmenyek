package hu.renesans.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import hu.renesans.eredmenyek.interactor.InteractorModule;
import hu.renesans.eredmenyek.mock.MockNetworkModule;
import hu.renesans.eredmenyek.repository.RepositoryModule;
import hu.renesans.eredmenyek.ui.UIModule;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, MockNetworkModule.class})
public interface MockEredmenyekApplicationComponent extends EredmenyekApplicationComponent {
}
