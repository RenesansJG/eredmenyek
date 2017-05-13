package hu.renesans.eredmenyek.interactor.matches;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.renesans.eredmenyek.interactor.matches.event.FindMatchesByTeamEvent;
import hu.renesans.eredmenyek.interactor.matches.event.FindMatchesByTournamentEvent;
import hu.renesans.eredmenyek.interactor.matches.event.GetMatchEvent;
import hu.renesans.eredmenyek.model.Match;
import hu.renesans.eredmenyek.model.MatchHeader;
import hu.renesans.eredmenyek.network.matches.MatchesApi;
import retrofit2.Call;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.utils.NetworkHelper.executeCall;

public class MatchesInteractor {
    @Inject
    EventBus bus;

    @Inject
    MatchesApi api;

    public MatchesInteractor() {
        injector.inject(this);
    }

    public void findMatchesByTournament(long id) {
        Call<List<MatchHeader>> call = api.findMatchesByTournament(id);
        FindMatchesByTournamentEvent event = new FindMatchesByTournamentEvent();
        executeCall(call, event, bus);
    }

    public void findMatchesByTeam(long id) {
        Call<List<MatchHeader>> call = api.findMatchesByTeam(id);
        FindMatchesByTeamEvent event = new FindMatchesByTeamEvent();
        executeCall(call, event, bus);
    }

    public void getMatch(int id) {
        Call<Match> call = api.getMatch(id);
        GetMatchEvent event = new GetMatchEvent();
        executeCall(call, event, bus);
    }
}
