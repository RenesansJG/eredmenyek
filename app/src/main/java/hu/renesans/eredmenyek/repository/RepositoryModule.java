package hu.renesans.eredmenyek.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    public Repository provideRepository() {
        return new SugarOrmRepository();
    }
}
