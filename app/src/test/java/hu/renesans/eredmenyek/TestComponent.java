package hu.renesans.eredmenyek;

import javax.inject.Singleton;

import dagger.Component;
import hu.renesans.eredmenyek.interactor.InteractorModule;
import hu.renesans.eredmenyek.mock.MockNetworkModule;
import hu.renesans.eredmenyek.repository.TestRepositoryModule;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends EredmenyekApplicationComponent {
}
