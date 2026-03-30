package org.skypro.skyshop.model.basket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductBasket {
    private final Map<UUID, BasketItem> items = new HashMap<>();

    public void addItem(UUID productId, int quantity) {
        items.merge(productId, new BasketItem(productId, quantity),
                (oldItem, newItem) -> new BasketItem(productId, oldItem.getQuantity() + newItem.getQuantity()));
    }

    public Map<UUID, BasketItem> getItems() { return items; }
}