package hu.renesans.eredmenyek.model;

public interface Item<T extends Item<T>> {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getImageUrl();

    void setImageUrl(String imageUrl);
}
