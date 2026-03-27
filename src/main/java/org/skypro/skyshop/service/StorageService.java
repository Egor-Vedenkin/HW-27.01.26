package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        fillTestData();
    }

    private void fillTestData() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();
        UUID id5 = UUID.randomUUID();
        UUID id6 = UUID.randomUUID();
        UUID id7 = UUID.randomUUID();
        UUID id8 = UUID.randomUUID();
        UUID id9 = UUID.randomUUID();
        UUID id10 = UUID.randomUUID();
        UUID id11 = UUID.randomUUID();
        UUID id12 = UUID.randomUUID();
        UUID id13 = UUID.randomUUID();
        UUID id14 = UUID.randomUUID();
        UUID id15 = UUID.randomUUID();
        UUID id16 = UUID.randomUUID();
        UUID id17 = UUID.randomUUID();
        UUID id18 = UUID.randomUUID();
        UUID id19 = UUID.randomUUID();
        UUID id20 = UUID.randomUUID();

        products.put(id1, new SimpleProduct(id1, "Хлеб", 50));
        products.put(id2, new DiscountedProduct(id2, "Молоко", 100, 10));
        // ... остальные товары с уникальными id

        articles.put(id3, new Article(id3, "JavaScript основы", "Здесь рассказывается о JS..."));
        articles.put(id4, new Article(id4, "Основы Java", "Изучаем основы программирования на Java..."));
        // ... остальные статьи с уникальными id
    }

    public Collection<Product> getAllProducts() { return products.values(); }
    public Collection<Article> getAllArticles() { return articles.values(); }

    public Collection<Searchable> getAllSearchables() {
        return Stream.concat(products.values().stream(), articles.values().stream())
                .collect(Collectors.toList());
    }
}