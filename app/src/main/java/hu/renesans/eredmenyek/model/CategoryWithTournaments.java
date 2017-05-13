package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryWithTournaments extends Category {
    @SerializedName("tournaments")
    protected List<Tournament> tournaments;

    public CategoryWithTournaments() {
        super();
    }

    public CategoryWithTournaments(Long id, String name, String imageUrl, List<Tournament> tournaments) {
        super(id, name, imageUrl);
        this.tournaments = tournaments;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
