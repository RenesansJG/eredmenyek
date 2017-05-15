package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryWithTeams extends Team implements Category<Team> {
    @SerializedName("teams")
    protected List<Team> teams;

    public CategoryWithTeams() {
        super();
    }

    public CategoryWithTeams(Long id, String name, String imageUrl, List<Team> teams) {
        super(id, name, imageUrl);
        this.teams = teams;
    }

    @Override
    public List<Team> getItems() {
        return teams;
    }

    @Override
    public void setItems(List<Team> items) {
        teams = items;
    }
}
