package hu.renesans.eredmenyek.interactor.teams.event;

import java.util.List;

import hu.renesans.eredmenyek.model.CategoryWithTeams;
import hu.renesans.eredmenyek.utils.BusEvent;

public class GetTeamsEvent extends BusEvent<List<CategoryWithTeams>> {
}
