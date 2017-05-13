package hu.renesans.eredmenyek.interactor.favorites.events;

import java.util.List;

import hu.renesans.eredmenyek.model.Team;
import hu.renesans.eredmenyek.utils.BusEvent;

public class GetFavoriteTeamsEvent extends BusEvent<List<Team>> {
}
