package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final UUID id;
    protected final String title;

    public Product(UUID id, String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Название товара не может быть пустым или null");
        this.id = id;
        this.title = title;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }

    @Override
    @JsonIgnore
    public String getSearchTerm() { return title; }

    @Override
    @JsonIgnore
    public String getContentType() { return "PRODUCT"; }

    @Override
    public String getName() { return title; }

    public abstract int getPrice();
    public abstract boolean isSpecial();
}