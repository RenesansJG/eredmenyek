package hu.renesans.eredmenyek.model.database;

import com.orm.dsl.Table;

@Table
public class Team {
    private Long id;
    private String name;
    private String imageUrl;

    public Team() {
    }

    public Team(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public hu.renesans.eredmenyek.model.Team toModelObject() {
        return new hu.renesans.eredmenyek.model.Team(this.id, this.name, this.imageUrl);
    }
}
