package hu.renesans.eredmenyek.model;

import com.google.gson.annotations.SerializedName;

public abstract class BaseItem<T extends Item<T>> implements Item<T> {
    @SerializedName("id")
    protected Long id;

    @SerializedName("name")
    protected String name;

    @SerializedName("imageUrl")
    protected String imageUrl;

    protected BaseItem() {
    }

    protected BaseItem(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
