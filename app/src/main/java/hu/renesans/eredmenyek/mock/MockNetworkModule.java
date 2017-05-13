package hu.renesans.eredmenyek.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.renesans.eredmenyek.network.NetworkModule;
import hu.renesans.eredmenyek.network.matches.MatchesApi;
import hu.renesans.eredmenyek.network.teams.TeamsApi;
import hu.renesans.eredmenyek.network.tournaments.TournamentsApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        builder.interceptors().add(chain -> MockHttpServer.call(chain.request()));
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofit(client);
    }

    @Provides
    @Singleton
    public TournamentsApi provideTournamentsApi(Retrofit retrofit) {
        return networkModule.provideTournamentsApi(retrofit);
    }

    @Provides
    @Singleton
    public TeamsApi provideTeamsApi(Retrofit retrofit) {
        return networkModule.provideTeamsApi(retrofit);
    }

    @Provides
    @Singleton
    public MatchesApi provideMatchesApi(Retrofit retrofit) {
        return networkModule.provideMatchesApi(retrofit);
    }
}
