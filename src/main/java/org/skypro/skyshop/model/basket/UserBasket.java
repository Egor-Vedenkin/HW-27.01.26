package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int total;

    // Конструктор принимает только список товаров
    private UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = calculateTotal(items); // Сумма считается внутри
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    // Статический фабричный метод для создания из ProductBasket
    public static UserBasket fromProductBasket(ProductBasket basket, StorageService storage) {
        Map<UUID, Integer> map = basket.getItems();

        List<BasketItem> items = map.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int qty = entry.getValue();
                    Product prod = storage.getProductById(id).orElseThrow();
                    return new BasketItem(prod, qty);
                })
                .collect(Collectors.toList());

        return new UserBasket(items);
    }

    // Выделенный метод для подсчета суммы (по требованию)
    private static int calculateTotal(List<BasketItem> items) {
        return items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}