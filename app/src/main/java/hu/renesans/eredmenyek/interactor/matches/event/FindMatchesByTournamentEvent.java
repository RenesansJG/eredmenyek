package hu.renesans.eredmenyek.interactor.matches.event;

import java.util.List;

import hu.renesans.eredmenyek.model.MatchHeader;
import hu.renesans.eredmenyek.utils.BusEvent;

public class FindMatchesByTournamentEvent extends BusEvent<List<MatchHeader>> {
}
