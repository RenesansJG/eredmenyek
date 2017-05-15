package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryWithTournaments extends Tournament implements Category<Tournament> {
    @SerializedName("tournaments")
    protected List<Tournament> tournaments;

    public CategoryWithTournaments() {
        super();
    }

    public CategoryWithTournaments(Long id, String name, String imageUrl, List<Tournament> tournaments) {
        super(id, name, imageUrl);
        this.tournaments = tournaments;
    }

    @Override
    public List<Tournament> getItems() {
        return tournaments;
    }

    @Override
    public void setItems(List<Tournament> items) {
        tournaments = items;
    }
}
