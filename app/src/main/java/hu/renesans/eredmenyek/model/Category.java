package hu.renesans.eredmenyek.model;

import java.util.List;

public interface Category<T extends Item<T>> extends Item<T> {
    List<T> getItems();

    void setItems(List<T> items);
}
