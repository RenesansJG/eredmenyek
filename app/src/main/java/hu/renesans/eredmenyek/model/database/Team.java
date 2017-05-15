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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team other = (Team) o;

        return (id != null ? id.equals(other.id) : other.id == null) &&
                (name != null ? name.equals(other.name) : other.name == null) &&
                (imageUrl != null ? imageUrl.equals(other.imageUrl) : other.imageUrl == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
