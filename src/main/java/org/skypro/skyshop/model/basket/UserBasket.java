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

    private UserBasket(List<BasketItem> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public static UserBasket fromProductBasket(ProductBasket basket, StorageService storage) {
        Map<UUID, Integer> map = basket.getItems();

        List<BasketItem> items = map.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int qty = entry.getValue();
                    Product prod = storage.getProductById(id).orElseThrow(); // Безопасно, так как товары добавлялись через сервис
                    return new BasketItem(prod, qty);
                })
                .collect(Collectors.toList());

        int total = items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(items, total);
    }
}