package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import java.util.List;
import java.util.stream.Collectors;

public class UserBasket {

    private final List<BasketItem> items; // Только список товаров корзины.

    // Итоговая стоимость корзины.
    private final int total;

    // Приватный конструктор — только через фабричный метод.
    private UserBasket(List<BasketItem> items, int total) {
        this.items = items;
        this.total = total;
    }

    // Фабричный метод для создания UserBasket из списка BasketItem.
    // Здесь же происходит подсчёт total.
    public static UserBasket fromBasketItems(List<BasketItem> items, StorageService storageService) {

        // Подсчёт общей суммы через StreamAPI.
        int total = calculateTotal(items, storageService);

        return new UserBasket(items, total);
    }

    // Выделен отдельный метод для подсчёта total.
    private static int calculateTotal(List<BasketItem> items, StorageService storageService) {

        return items.stream()
                .mapToInt(item -> {
                    Product product = storageService.getAllProducts().stream()
                            .filter(p -> p.getId().equals(item.getProductId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Товар не найден"));
                    return product.getPrice() * item.getQuantity();
                })
                .sum();
        }
    }
