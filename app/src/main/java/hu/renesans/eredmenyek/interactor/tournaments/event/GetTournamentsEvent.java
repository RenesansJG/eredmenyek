package hu.renesans.eredmenyek.interactor.tournaments.event;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTournaments;
import hu.renesans.eredmenyek.utils.BusEvent;

public class GetTournamentsEvent extends BusEvent<List<CategoryWithTournaments>> {
}
