package hu.renesans.eredmenyek.interactor.favorites.events;

import java.util.List;

import hu.renesans.eredmenyek.model.Tournament;
import hu.renesans.eredmenyek.utils.BusEvent;

public class GetFavoriteTournamentsEvent extends BusEvent<List<Tournament>> {
}
