package hu.renesans.eredmenyek.model;

public class Tournament extends Item {
    public Tournament() {
        super();
    }

    public Tournament(Long id, String name, String imageUrl) {
        super(id, name, imageUrl);
    }

    public hu.renesans.eredmenyek.model.database.Tournament toDbObject() {
        return new hu.renesans.eredmenyek.model.database.Tournament(this.getId(), this.getName(), this.getImageUrl());
    }
}
