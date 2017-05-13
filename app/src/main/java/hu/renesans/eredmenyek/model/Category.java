package hu.renesans.eredmenyek.model;

public abstract class Category extends Item {
    public Category() {
        super();
    }

    public Category(Long id, String name, String imageUrl) {
        super(id, name, imageUrl);
    }
}
