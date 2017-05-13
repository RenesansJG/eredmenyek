package hu.renesans.eredmenyek.interactor.teams;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.teams.event.GetTeamsEvent;
import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.network.teams.TeamsApi;
import retrofit2.Call;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.utils.NetworkHelper.executeCall;

public class TeamsInteractor {
    @Inject
    EventBus bus;

    @Inject
    TeamsApi api;

    public TeamsInteractor() {
        injector.inject(this);
    }

    public void getTeams() {
        Call<List<CategoryWithTeams>> call = api.getTeams();
        GetTeamsEvent event = new GetTeamsEvent();
        executeCall(call, event, bus);
    }
}
