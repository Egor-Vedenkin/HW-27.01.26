package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Метод для добавления товара в корзину
    public void addProductToBasket(UUID productId) {
        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        productBasket.addProduct(product.getId());
    }

    // Основной метод — реализует всю логику внутри
    public UserBasket getUserBasket() {
        // Получаем содержимое корзины (Map<UUID, Integer>)
        Map<UUID, Integer> basketItems = productBasket.getItems();

        // Преобразуем в список BasketItem
        List<BasketItem> items = basketItems.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        // Создаём UserBasket через фабричный метод
        return UserBasket.fromItems(items);
    }
}