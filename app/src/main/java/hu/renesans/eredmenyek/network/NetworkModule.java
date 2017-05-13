package hu.renesans.eredmenyek.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.renesans.eredmenyek.network.matches.MatchesApi;
import hu.renesans.eredmenyek.network.teams.TeamsApi;
import hu.renesans.eredmenyek.network.tournaments.TournamentsApi;
import hu.renesans.eredmenyek.utils.GsonHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public TournamentsApi provideTournamentsApi(Retrofit retrofit) {
        return retrofit.create(TournamentsApi.class);
    }

    @Provides
    @Singleton
    public TeamsApi provideTeamsApi(Retrofit retrofit) {
        return retrofit.create(TeamsApi.class);
    }

    @Provides
    @Singleton
    public MatchesApi provideMatchesApi(Retrofit retrofit) {
        return retrofit.create(MatchesApi.class);
    }
}
