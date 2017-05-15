package hu.renesans.eredmenyek.model;

public class Team extends BaseItem<Team> {
    public Team() {
        super();
    }

    public Team(Long id, String name, String imageUrl) {
        super(id, name, imageUrl);
    }

    public hu.renesans.eredmenyek.model.database.Team toDbObject() {
        return new hu.renesans.eredmenyek.model.database.Team(this.getId(), this.getName(), this.getImageUrl());
    }
}
