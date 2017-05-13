package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryWithTeams extends Category {
    @SerializedName("teams")
    protected List<Team> teams;

    public CategoryWithTeams() {
        super();
    }

    public CategoryWithTeams(Long id, String name, String imageUrl, List<Team> teams) {
        super(id, name, imageUrl);
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
