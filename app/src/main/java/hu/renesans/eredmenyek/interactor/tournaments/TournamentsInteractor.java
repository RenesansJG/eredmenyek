package hu.renesans.eredmenyek.interactor.tournaments;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.tournaments.event.GetTournamentsEvent;
import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.network.tournaments.TournamentsApi;
import retrofit2.Call;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.utils.NetworkHelper.executeCall;

public class TournamentsInteractor {
    @Inject
    EventBus bus;

    @Inject
    TournamentsApi api;

    public TournamentsInteractor() {
        injector.inject(this);
    }

    public void getTournaments() {
        Call<List<CategoryWithTournaments>> call = api.getTournaments();
        GetTournamentsEvent event = new GetTournamentsEvent();
        executeCall(call, event, bus);
    }
}
