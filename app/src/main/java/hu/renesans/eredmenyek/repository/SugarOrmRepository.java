package hu.renesans.eredmenyek.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import hu.renesans.eredmenyek.model.database.Team;
import hu.renesans.eredmenyek.model.database.Tournament;

public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Tournament> getFavoriteTournaments() {
        return SugarRecord.listAll(Tournament.class);
    }

    @Override
    public List<Team> getFavoriteTeams() {
        return SugarRecord.listAll(Team.class);
    }

    @Override
    public void saveFavorite(Tournament tournament) {
        SugarRecord.saveInTx(tournament);
    }

    @Override
    public void saveFavorite(Team team) {
        SugarRecord.saveInTx(team);
    }

    @Override
    public void removeFavorite(Tournament tournament) {
        SugarRecord.deleteInTx(tournament);
    }

    @Override
    public void removeFavorite(Team team) {
        SugarRecord.deleteInTx(team);
    }

    @Override
    public boolean isInDb(Tournament tournament) {
        return SugarRecord.findById(Tournament.class, tournament.getId()) != null;
    }

    @Override
    public boolean isInDb(Team team) {
        return SugarRecord.findById(Team.class, team.getId()) != null;
    }
}
